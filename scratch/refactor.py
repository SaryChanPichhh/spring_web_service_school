import os
import re

base_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\web_service\src\main\java\com\example\web_service\feature"
models = {}

# Discover all models and their correct capitalization
for root, dirs, files in os.walk(base_dir):
    if "model" in root.split(os.sep):
        for file in files:
            if file.endswith(".java"):
                model_name = file[:-5]
                models[model_name] = True
                
models["Category"] = True # Category is in category/model
                
print("Discovered models:", list(models.keys()))

model_fields = {} # mapping of (dto_class_name, field_name) -> model_name

# Update DTOs
for root, dirs, files in os.walk(base_dir):
    for file in files:
        if file.endswith("Request.java") or file.endswith("RequestUpdate.java"):
            file_path = os.path.join(root, file)
            with open(file_path, "r", encoding="utf-8") as f:
                content = f.read()

            lines = content.split('\n')
            new_lines = []
            modified = False
            
            dto_name = file[:-5]
            
            for line in lines:
                # Match "    ModelName fieldName," or without comma
                match = re.search(r'^\s*([A-Z]\w+)\s+(\w+)(,)?$', line)
                if match:
                    field_type, field_name, comma = match.groups()
                    if field_type in models:
                        comma_str = "," if comma else ""
                        new_line = f"    Integer {field_name}Id{comma_str}"
                        new_lines.append(new_line)
                        modified = True
                        model_fields[(dto_name, field_name)] = field_type
                        continue
                
                # We can keep imports, java compiler might just warn about unused imports
                new_lines.append(line)
            
            if modified:
                with open(file_path, "w", encoding="utf-8") as f:
                    f.write('\n'.join(new_lines))
                print(f"Updated {file_path}")

# Update Mappers
for root, dirs, files in os.walk(base_dir):
    if "mapper" in root.split(os.sep):
        for file in files:
            if file.endswith("Mapper.java"):
                file_path = os.path.join(root, file)
                with open(file_path, "r", encoding="utf-8") as f:
                    content = f.read()
                
                lines = content.split('\n')
                new_lines = []
                modified = False
                
                feature_name = root.split(os.sep)[-2]
                mapper_name = file[:-5]
                req_dto = mapper_name.replace("Mapper", "Request")
                req_up_dto = mapper_name.replace("Mapper", "RequestUpdate")
                
                i = 0
                while i < len(lines):
                    line = lines[i]
                    
                    # check for `entity.setX(req.y());`
                    match1 = re.search(r'^(\s*)entity\.set([A-Z]\w+)\(req\.(\w+)\(\)\);', line)
                    if match1:
                        indent = match1.group(1)
                        setter = match1.group(2)
                        getter = match1.group(3)
                        
                        # check if getter was a model field in req_dto
                        model_type = model_fields.get((req_dto, getter))
                        if model_type:
                            modified = True
                            new_lines.append(f"{indent}if (req.{getter}Id() != null) {{")
                            new_lines.append(f"{indent}    {model_type} _m = new {model_type}();")
                            # Try to figure out ID type? We assume Integer since we changed DTO to Integer.
                            # Even if model ID is Long, we cast or just setId
                            new_lines.append(f"{indent}    _m.setId(req.{getter}Id());")
                            new_lines.append(f"{indent}    entity.set{setter}(_m);")
                            new_lines.append(f"{indent}}}")
                            i += 1
                            continue

                    # check for `if (req.y() != null) { ... }` in updateFromRequest
                    match2 = re.search(r'^(\s*)if \(req\.(\w+)\(\) != null\) \{', line)
                    if match2 and (i+1 < len(lines)) and (i+2 < len(lines)):
                        indent = match2.group(1)
                        getter = match2.group(2)
                        model_type = model_fields.get((req_up_dto, getter))
                        if model_type:
                            match3 = re.search(r'entity\.set([A-Z]\w+)\(', lines[i+1])
                            if match3:
                                setter = match3.group(1)
                                modified = True
                                new_lines.append(f"{indent}if (req.{getter}Id() != null) {{")
                                new_lines.append(f"{indent}    {model_type} _m = new {model_type}();")
                                new_lines.append(f"{indent}    _m.setId(req.{getter}Id());")
                                new_lines.append(f"{indent}    entity.set{setter}(_m);")
                                new_lines.append(f"{indent}}}")
                                i += 3 # skip the original if block
                                continue
                                
                    # If this file needs imports for models it creates, we could add them, 
                    # but they might already be there if the old DTO used them.
                    # Actually, the old DTO was in dto/req, the Mapper imports the model.
                    # e.g., `import com.example.web_service.feature.admin.restaurant.model.Restaurant;`
                    # Since it was already there for the getter/setter, it should be fine!
                    
                    new_lines.append(line)
                    i += 1
                    
                if modified:
                    with open(file_path, "w", encoding="utf-8") as f:
                        f.write('\n'.join(new_lines))
                    print(f"Updated {file_path}")
                    
print("Done refactoring.")

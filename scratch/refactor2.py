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
                # feature name could be extracted from root path
                feature = root.split(os.sep)[-2]
                pkg = "admin." + feature if feature != "category" else "category"
                
                fqdn = f"com.example.web_service.feature.{pkg}.model.{model_name}"
                models[model_name] = fqdn

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
                # We already replaced "ModelName fieldName," with "Integer fieldNameId,"
                # So we need to parse the previous state if we want to run again? 
                # NO, if we ran it once, we can just extract from the updated file?
                # Actually, in the DTO it's already "Integer fieldNameId," so we can't extract it.
                # BUT wait, we just need to fix the mappers!
                pass

# Let's rebuild model_fields from the DTOs imports or just hardcode for now?
# Or we can just read the original fields from generate_services.py or just use regex on the mapper itself!
# In the mapper we have: `Restaurant _m = new Restaurant();`
# We can just replace `Type _m = new Type();` with `FQDN _m = new FQDN();`

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
                
                for line in lines:
                    match = re.search(r'^\s*([A-Z]\w+)\s+_m\s*=\s*new\s+([A-Z]\w+)\(\);', line)
                    if match:
                        model_type = match.group(1)
                        if model_type in models:
                            fqdn = models[model_type]
                            new_line = line.replace(model_type, fqdn)
                            new_lines.append(new_line)
                            modified = True
                            continue
                            
                    new_lines.append(line)
                    
                if modified:
                    with open(file_path, "w", encoding="utf-8") as f:
                        f.write('\n'.join(new_lines))
                    print(f"Updated {file_path}")
                    
print("Done refactoring Mappers.")

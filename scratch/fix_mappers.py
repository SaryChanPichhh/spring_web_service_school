import os
import re

base_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\web_service\src\main\java\com\example\web_service\feature"

models_info = {} # fqdn -> (id_field_name, id_field_type)

for root, dirs, files in os.walk(base_dir):
    if "model" in root.split(os.sep):
        for file in files:
            if file.endswith(".java"):
                file_path = os.path.join(root, file)
                with open(file_path, "r", encoding="utf-8") as f:
                    content = f.read()
                
                lines = content.split('\n')
                id_type = None
                id_name = None
                for i, line in enumerate(lines):
                    if "@Id" in line:
                        for j in range(i+1, min(i+4, len(lines))):
                            match = re.search(r'private\s+(\w+)\s+(\w+);', lines[j])
                            if match:
                                id_type = match.group(1)
                                id_name = match.group(2)
                                break
                        break
                
                model_name = file[:-5]
                feature = root.split(os.sep)[-2]
                pkg = "admin." + feature if feature != "category" else "category"
                fqdn = f"com.example.web_service.feature.{pkg}.model.{model_name}"
                
                if id_name:
                    models_info[fqdn] = (id_name, id_type)
                else:
                    models_info[fqdn] = ("id", "Long")

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
                current_fqdn = None
                
                for line in lines:
                    match_new = re.search(r'([A-Za-z0-9_\.]+)\s+_m\s*=\s*new\s+\1\(\);', line)
                    if match_new:
                        current_fqdn = match_new.group(1)
                        
                    match_set = re.search(r'^(\s*)_m\.setId\((req\.\w+\(\))\);', line)
                    if match_set and current_fqdn:
                        indent = match_set.group(1)
                        getter_call = match_set.group(2)
                        
                        id_name, id_type = models_info.get(current_fqdn, ("id", "Long"))
                        setter_name = "set" + id_name[0].upper() + id_name[1:]
                        
                        if id_type == "Long":
                            val = f"(long) {getter_call}"
                        else:
                            val = getter_call
                            
                        new_line = f"{indent}_m.{setter_name}({val});"
                        new_lines.append(new_line)
                        modified = True
                        current_fqdn = None 
                        continue
                        
                    new_lines.append(line)
                    
                if modified:
                    with open(file_path, "w", encoding="utf-8") as f:
                        f.write('\n'.join(new_lines))
                    print(f"Updated {file_path}")

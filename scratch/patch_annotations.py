import os
import re

old_models_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\food_delivery_app_backend\src\main\java\com\example\food_delivery_app\models"
new_models_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\web_service\src\main\java\com\example\web_service\feature\admin"

old_files = [f for f in os.listdir(old_models_dir) if f.endswith('.java')]

for file in old_files:
    with open(os.path.join(old_models_dir, file), 'r', encoding='utf-8') as f:
        old_content = f.read()
    
    m = re.search(r'public class (\w+)', old_content)
    if not m: continue
    old_class = m.group(1)
    
    if old_class.endswith('Model'):
        entity = old_class[:-5]
    else:
        entity = old_class
        
    if entity in ['Sample', 'Category']:
        continue
        
    feature = entity.lower()
    new_model_path = os.path.join(new_models_dir, feature, 'model', f"{entity}.java")
    if not os.path.exists(new_model_path):
        continue
        
    with open(new_model_path, 'r', encoding='utf-8') as f:
        new_content = f.read()
        
    matches = re.finditer(r'((?:@[A-Za-z0-9_]+(?:\([^)]*\))?\s+)+)private\s+(?:[\w<>,\s]+)\s+(\w+)\s*(?:=|;)', old_content)
    
    replacements = {}
    for match in matches:
        annotations_raw = match.group(1)
        field_name = match.group(2)
        
        valid_annos = re.findall(r'(@(?:ManyToOne|OneToMany|ManyToMany|OneToOne|JoinColumn|JoinTable|JsonIgnore|JsonBackReference|JsonManagedReference)(?:\([^)]*\))?)', annotations_raw)
        
        if valid_annos:
            replacements[field_name] = '\n    '.join(valid_annos)
            
    if replacements:
        new_lines = new_content.split('\n')
        out_lines = []
        for line in new_lines:
            m_field = re.search(r'^\s*private\s+(?:[\w<>,\s]+)\s+(\w+)\s*;', line)
            if m_field:
                f_name = m_field.group(1)
                if f_name in replacements:
                    out_lines.append('    ' + replacements[f_name])
            out_lines.append(line)
            
        with open(new_model_path, 'w', encoding='utf-8') as f:
            f.write('\n'.join(out_lines))
            
print("Patching complete.")

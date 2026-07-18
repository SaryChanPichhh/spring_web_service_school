import os
import re

admin_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\web_service\src\main\java\com\example\web_service\feature\admin"
base_pkg = "com.example.web_service.feature.admin"

def to_pascal(s):
    return s[0].upper() + s[1:]

features = [d for d in os.listdir(admin_dir) if os.path.isdir(os.path.join(admin_dir, d))]

for feature in features:
    feature_dir = os.path.join(admin_dir, feature)
    model_dir = os.path.join(feature_dir, "model")
    if not os.path.exists(model_dir):
        continue
    
    # find entity name
    entity_file = [f for f in os.listdir(model_dir) if f.endswith(".java")][0]
    entity_name = entity_file[:-5]
    
    # Read model fields
    with open(os.path.join(model_dir, entity_file), 'r', encoding='utf-8') as f:
        content = f.read()
    
    fields = []
    # match private fields
    lines = content.split('\n')
    for line in lines:
        m = re.search(r'^\s*private\s+(?:[\w<>,\s]+)\s+(\w+)\s*;', line)
        if m:
            field_name = m.group(1)
            # Find its type roughly
            type_m = re.search(r'^\s*private\s+([\w<>,]+(?:\[\])?)\s+', line)
            if type_m:
                field_type = type_m.group(1)
                fields.append((field_type, field_name))
                
    # Now generate the manual mapper
    mapper_path = os.path.join(feature_dir, "mapper", f"{entity_name}Mapper.java")
    pkg = f"{base_pkg}.{feature}"
    
    # gather imports from model just in case, but actually we can just import model and dtos
    # We might need java.util.List, java.util.stream.Collectors
    with open(mapper_path, 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.mapper;\n\n")
        f.write(f"import {pkg}.dto.req.*;\n")
        f.write(f"import {pkg}.dto.res.*;\n")
        f.write(f"import {pkg}.model.{entity_name};\n")
        f.write("import org.springframework.stereotype.Component;\n")
        f.write("import java.util.List;\n")
        f.write("import java.util.stream.Collectors;\n\n")
        f.write("@Component\n")
        f.write(f"public class {entity_name}Mapper {{\n\n")
        
        # fromRequest
        f.write(f"    public {entity_name} fromRequest({entity_name}Request req) {{\n")
        f.write(f"        if (req == null) return null;\n")
        f.write(f"        {entity_name} entity = new {entity_name}();\n")
        for f_type, f_name in fields:
            if f_name == 'id' or f_name.endswith('Id'):
                continue
            setter = f"set{to_pascal(f_name)}"
            f.write(f"        entity.{setter}(req.{f_name}());\n")
        f.write(f"        return entity;\n")
        f.write(f"    }}\n\n")
        
        # toResponse
        f.write(f"    public {entity_name}Response toResponse({entity_name} entity) {{\n")
        f.write(f"        if (entity == null) return null;\n")
        f.write(f"        {entity_name}Response res = new {entity_name}Response();\n")
        for f_type, f_name in fields:
            getter = f"get{to_pascal(f_name)}"
            if f_type.lower() == 'boolean' or f_type.lower() == 'bool':
                # Sometimes boolean getters are isField
                if not f_name.startswith('is'):
                    getter = f"get{to_pascal(f_name)}"
                else:
                    getter = f_name
            setter = f"set{to_pascal(f_name)}"
            f.write(f"        res.{setter}(entity.{getter}());\n")
        f.write(f"        return res;\n")
        f.write(f"    }}\n\n")
        
        # toResponseList
        f.write(f"    public List<{entity_name}Response> toResponseList(List<{entity_name}> entities) {{\n")
        f.write(f"        if (entities == null) return null;\n")
        f.write(f"        return entities.stream().map(this::toResponse).collect(Collectors.toList());\n")
        f.write(f"    }}\n\n")
        
        # updateFromRequest
        f.write(f"    public void updateFromRequest({entity_name} entity, {entity_name}RequestUpdate req) {{\n")
        f.write(f"        if (req == null) return;\n")
        for f_type, f_name in fields:
            if f_name == 'id' or f_name.endswith('Id'):
                continue
            setter = f"set{to_pascal(f_name)}"
            f.write(f"        if (req.{f_name}() != null) {{\n")
            f.write(f"            entity.{setter}(req.{f_name}());\n")
            f.write(f"        }}\n")
        f.write(f"    }}\n")
        
        f.write("}\n")
        
print("Mappers converted successfully.")

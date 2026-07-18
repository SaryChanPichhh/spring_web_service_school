import os
import re

old_models_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\food_delivery_app_backend\src\main\java\com\example\food_delivery_app\models"
new_features_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\web_service\src\main\java\com\example\web_service\feature\admin"
base_pkg = "com.example.web_service.feature.admin"

all_entities = set()
models_info = {}

fields_to_ignore = ['createdAt', 'createdBy', 'updatedAt', 'updatedBy', 'isDeleted', 'isUpdated']

def parse_model(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()

    class_match = re.search(r'public class (\w+)', content)
    if not class_match:
        return None
    
    old_class_name = class_match.group(1)
    if old_class_name.endswith('Model'):
        entity_name = old_class_name[:-5]
    else:
        entity_name = old_class_name
        
    feature_name = entity_name.lower()
    
    if entity_name in ['Sample', 'Category']: 
        return None

    fields = []
    lines = content.split('\n')
    for line in lines:
        line = line.split('//')[0].strip()
        if line.startswith('private '):
            line = line.split('=')[0].split(';')[0].strip()
            parts = line.split()
            if len(parts) >= 3:
                field_type = parts[-2]
                field_name = parts[-1]
                if field_name in fields_to_ignore:
                    continue
                if field_type == 'boolean':
                    field_type = 'Boolean'
                elif field_type == 'int':
                    field_type = 'Integer'
                elif field_type == 'double':
                    field_type = 'Double'
                elif field_type == 'long':
                    field_type = 'Long'
                fields.append((field_type, field_name))
                
    return entity_name, feature_name, fields, content

def generate_feature(entity_name, feature_name, fields, old_content):
    feature_dir = os.path.join(new_features_dir, feature_name)
    os.makedirs(feature_dir, exist_ok=True)
    
    pkg = f"{base_pkg}.{feature_name}"
    
    processed_fields = []
    imports = set()
    
    enums = ['AssignmentType', 'AssignmentStatus', 'DiscountValue', 'InvoiceType', 'PaymentMethod', 'DiscountType', 'CouponStatus']
    
    for t, n in fields:
        if 'List' in t:
            imports.add('import java.util.List;\n')
            
        t_clean = re.sub(r'Model\b', '', t)
        
        words = re.findall(r'\b[A-Z]\w+\b', t_clean)
        for w in words:
            if w in enums:
                t_clean = t_clean.replace(w, 'String')
            elif w in all_entities or w == 'Category':
                if w == 'Category':
                    imports.add('import com.example.web_service.feature.category.model.Category;\n')
                else:
                    imports.add(f"import com.example.web_service.feature.admin.{w.lower()}.model.{w};\n")
                    
        processed_fields.append((t_clean, n))
    fields = processed_fields
    
    imports_str = "".join(sorted(list(imports)))
    
    # 1. model
    os.makedirs(os.path.join(feature_dir, 'model'), exist_ok=True)
    with open(os.path.join(feature_dir, 'model', f"{entity_name}.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.model;\n\n")
        f.write(f"import com.example.web_service.shared.BasedEntity;\n")
        f.write("import jakarta.persistence.*;\n")
        f.write("import lombok.*;\n")
        f.write("import java.time.LocalDate;\n")
        f.write("import java.time.LocalDateTime;\n")
        f.write("import java.time.LocalTime;\n")
        f.write(imports_str)
        f.write("\n@Data\n@NoArgsConstructor\n@AllArgsConstructor\n@EqualsAndHashCode(callSuper = true)\n")
        f.write("@Entity\n")
        f.write(f'@Table(name = "{feature_name}s")\n')
        f.write(f"public class {entity_name} extends BasedEntity {{\n")
        for f_type, f_name in fields:
            if f_name == 'id' or f_name.endswith('Id'):
                f.write(f"    @Id\n    @GeneratedValue(strategy = GenerationType.IDENTITY)\n")
            f.write(f"    private {f_type} {f_name};\n")
        f.write("}\n")
        
    # 2. dto
    os.makedirs(os.path.join(feature_dir, 'dto', 'req'), exist_ok=True)
    os.makedirs(os.path.join(feature_dir, 'dto', 'res'), exist_ok=True)
    
    # req
    with open(os.path.join(feature_dir, 'dto', 'req', f"{entity_name}Request.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.dto.req;\n\n")
        f.write("import java.time.LocalDate;\n")
        f.write("import java.time.LocalDateTime;\n")
        f.write("import java.time.LocalTime;\n")
        f.write(imports_str)
        f.write(f"\npublic record {entity_name}Request(\n")
        params = [f"    {t} {n}" for t, n in fields if not (n == 'id' or n.endswith('Id'))]
        f.write(",\n".join(params))
        f.write("\n) {\n}\n")
        
    # req update
    with open(os.path.join(feature_dir, 'dto', 'req', f"{entity_name}RequestUpdate.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.dto.req;\n\n")
        f.write("import java.time.LocalDate;\n")
        f.write("import java.time.LocalDateTime;\n")
        f.write("import java.time.LocalTime;\n")
        f.write(imports_str)
        f.write(f"\npublic record {entity_name}RequestUpdate(\n")
        f.write(",\n".join(params))
        f.write("\n) {\n}\n")
        
    # res
    with open(os.path.join(feature_dir, 'dto', 'res', f"{entity_name}Response.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.dto.res;\n\n")
        f.write("import lombok.Getter;\nimport lombok.Setter;\n")
        f.write("import java.time.LocalDate;\n")
        f.write("import java.time.LocalDateTime;\n")
        f.write("import java.time.LocalTime;\n")
        f.write(imports_str)
        f.write("\n@Getter\n@Setter\n")
        f.write(f"public class {entity_name}Response {{\n")
        for t, n in fields:
            f.write(f"    private {t} {n};\n")
        f.write("}\n")

    # page res
    with open(os.path.join(feature_dir, 'dto', 'res', f"{entity_name}PageResponseDto.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.dto.res;\n\n")
        f.write("import lombok.Data;\nimport java.util.List;\n\n")
        f.write("@Data\n")
        f.write(f"public class {entity_name}PageResponseDto<T> {{\n")
        f.write("    private List<T> content;\n")
        f.write("    private int pageNumber;\n")
        f.write("    private int pageSize;\n")
        f.write("    private long totalElements;\n")
        f.write("    private int totalPages;\n")
        f.write("    private boolean last;\n")
        f.write("}\n")

    # 3. mapper
    os.makedirs(os.path.join(feature_dir, 'mapper'), exist_ok=True)
    with open(os.path.join(feature_dir, 'mapper', f"{entity_name}Mapper.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.mapper;\n\n")
        f.write(f"import {pkg}.dto.req.*;\n")
        f.write(f"import {pkg}.dto.res.*;\n")
        f.write(f"import {pkg}.model.{entity_name};\n")
        f.write("import org.mapstruct.*;\n")
        f.write(imports_str)
        f.write("\n@Mapper(componentModel = \"spring\", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)\n")
        f.write(f"public interface {entity_name}Mapper {{\n")
        f.write(f"    {entity_name} fromRequest({entity_name}Request req);\n")
        f.write(f"    {entity_name}Response toResponse({entity_name} entity);\n")
        f.write(f"    java.util.List<{entity_name}Response> toResponseList(java.util.List<{entity_name}> entities);\n")
        f.write(f"    void updateFromRequest(@MappingTarget {entity_name} entity, {entity_name}RequestUpdate reqUpdate);\n")
        f.write("}\n")

    # 4. repository
    os.makedirs(os.path.join(feature_dir, 'repository'), exist_ok=True)
    with open(os.path.join(feature_dir, 'repository', f"{entity_name}Repository.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.repository;\n\n")
        f.write(f"import {pkg}.model.{entity_name};\n")
        f.write("import org.springframework.data.jpa.repository.JpaRepository;\n")
        f.write("import org.springframework.stereotype.Repository;\n\n")
        f.write("@Repository\n")
        id_type = 'Long'
        for t, n in fields:
            if n == 'id' or n.endswith('Id'):
                id_type = t
                break
        f.write(f"public interface {entity_name}Repository extends JpaRepository<{entity_name}, {id_type}> {{\n}}\n")
        
    # 5. service
    os.makedirs(os.path.join(feature_dir, 'service'), exist_ok=True)
    with open(os.path.join(feature_dir, 'service', f"{entity_name}Service.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.service;\n\n")
        f.write(f"import {pkg}.dto.req.*;\n")
        f.write(f"import {pkg}.dto.res.*;\n")
        f.write("import java.util.List;\n\n")
        f.write(f"public interface {entity_name}Service {{\n")
        f.write(f"    List<{entity_name}Response> getAll();\n")
        f.write(f"    {entity_name}Response getById(Long id);\n")
        f.write(f"    {entity_name}Response create({entity_name}Request req);\n")
        f.write(f"    {entity_name}Response update(Long id, {entity_name}RequestUpdate reqUpdate);\n")
        f.write(f"    {entity_name}Response delete(Long id);\n")
        f.write(f"    {entity_name}PageResponseDto<{entity_name}Response> getPaginated(int page, int size);\n")
        f.write("}\n")
        
    # 6. serviceImpl
    os.makedirs(os.path.join(feature_dir, 'serviceImpl'), exist_ok=True)
    with open(os.path.join(feature_dir, 'serviceImpl', f"{entity_name}ServiceImplementation.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.serviceImpl;\n\n")
        f.write(f"import {pkg}.dto.req.*;\n")
        f.write(f"import {pkg}.dto.res.*;\n")
        f.write(f"import {pkg}.mapper.{entity_name}Mapper;\n")
        f.write(f"import {pkg}.model.{entity_name};\n")
        f.write(f"import {pkg}.repository.{entity_name}Repository;\n")
        f.write(f"import {pkg}.service.{entity_name}Service;\n")
        f.write("import lombok.RequiredArgsConstructor;\n")
        f.write("import org.springframework.stereotype.Service;\n")
        f.write("import org.springframework.data.domain.*;\n")
        f.write("import java.util.List;\n\n")
        f.write("@Service\n@RequiredArgsConstructor\n")
        f.write(f"public class {entity_name}ServiceImplementation implements {entity_name}Service {{\n")
        f.write(f"    private final {entity_name}Repository repository;\n")
        f.write(f"    private final {entity_name}Mapper mapper;\n\n")
        
        f.write("    @Override\n")
        f.write(f"    public List<{entity_name}Response> getAll() {{\n")
        f.write("        return mapper.toResponseList(repository.findAll());\n")
        f.write("    }\n\n")
        
        id_type = 'Long'
        for t, n in fields:
            if n == 'id' or n.endswith('Id'):
                id_type = t
                break
                
        f.write("    @Override\n")
        f.write(f"    public {entity_name}Response getById(Long id) {{\n")
        if id_type != 'Long':
            f.write(f"        {id_type} searchId = ({id_type}) (Object) id;\n")
            f.write(f"        {entity_name} entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException(\"Not found\"));\n")
        else:
            f.write(f"        {entity_name} entity = repository.findById(id).orElseThrow(() -> new RuntimeException(\"Not found\"));\n")
        f.write("        return mapper.toResponse(entity);\n")
        f.write("    }\n\n")
        
        f.write("    @Override\n")
        f.write(f"    public {entity_name}Response create({entity_name}Request req) {{\n")
        f.write(f"        {entity_name} entity = mapper.fromRequest(req);\n")
        f.write("        return mapper.toResponse(repository.save(entity));\n")
        f.write("    }\n\n")
        
        f.write("    @Override\n")
        f.write(f"    public {entity_name}Response update(Long id, {entity_name}RequestUpdate reqUpdate) {{\n")
        if id_type != 'Long':
            f.write(f"        {id_type} searchId = ({id_type}) (Object) id;\n")
            f.write(f"        {entity_name} entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException(\"Not found\"));\n")
        else:
            f.write(f"        {entity_name} entity = repository.findById(id).orElseThrow(() -> new RuntimeException(\"Not found\"));\n")
        f.write("        mapper.updateFromRequest(entity, reqUpdate);\n")
        f.write("        return mapper.toResponse(repository.save(entity));\n")
        f.write("    }\n\n")
        
        f.write("    @Override\n")
        f.write(f"    public {entity_name}Response delete(Long id) {{\n")
        if id_type != 'Long':
            f.write(f"        {id_type} searchId = ({id_type}) (Object) id;\n")
            f.write(f"        {entity_name} entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException(\"Not found\"));\n")
        else:
            f.write(f"        {entity_name} entity = repository.findById(id).orElseThrow(() -> new RuntimeException(\"Not found\"));\n")
        f.write("        repository.delete(entity);\n")
        f.write("        return mapper.toResponse(entity);\n")
        f.write("    }\n\n")
        
        f.write("    @Override\n")
        f.write(f"    public {entity_name}PageResponseDto<{entity_name}Response> getPaginated(int page, int size) {{\n")
        f.write(f"        Page<{entity_name}> pageResult = repository.findAll(PageRequest.of(page, size));\n")
        f.write(f"        {entity_name}PageResponseDto<{entity_name}Response> response = new {entity_name}PageResponseDto<>();\n")
        f.write("        response.setContent(mapper.toResponseList(pageResult.getContent()));\n")
        f.write("        response.setPageNumber(pageResult.getNumber());\n")
        f.write("        response.setPageSize(pageResult.getSize());\n")
        f.write("        response.setTotalElements(pageResult.getTotalElements());\n")
        f.write("        response.setTotalPages(pageResult.getTotalPages());\n")
        f.write("        response.setLast(pageResult.isLast());\n")
        f.write("        return response;\n")
        f.write("    }\n")
        f.write("}\n")
        
    # 7. controller
    os.makedirs(os.path.join(feature_dir, 'controller'), exist_ok=True)
    with open(os.path.join(feature_dir, 'controller', f"{entity_name}Controller.java"), 'w', encoding='utf-8') as f:
        f.write(f"package {pkg}.controller;\n\n")
        f.write(f"import {pkg}.dto.req.*;\n")
        f.write(f"import {pkg}.dto.res.*;\n")
        f.write(f"import {pkg}.service.{entity_name}Service;\n")
        f.write("import lombok.RequiredArgsConstructor;\n")
        f.write("import org.springframework.http.ResponseEntity;\n")
        f.write("import org.springframework.web.bind.annotation.*;\n")
        f.write("import java.util.List;\n\n")
        f.write("@RestController\n")
        f.write(f'@RequestMapping("/api/v1/admin/{feature_name}s")\n')
        f.write("@RequiredArgsConstructor\n")
        f.write(f"public class {entity_name}Controller {{\n")
        f.write(f"    private final {entity_name}Service service;\n\n")
        
        f.write("    @GetMapping\n")
        f.write(f"    public ResponseEntity<List<{entity_name}Response>> getAll() {{\n")
        f.write("        return ResponseEntity.ok(service.getAll());\n")
        f.write("    }\n\n")
        
        f.write("    @GetMapping(\"/{id}\")\n")
        f.write(f"    public ResponseEntity<{entity_name}Response> getById(@PathVariable Long id) {{\n")
        f.write("        return ResponseEntity.ok(service.getById(id));\n")
        f.write("    }\n\n")
        
        f.write("    @PostMapping\n")
        f.write(f"    public ResponseEntity<{entity_name}Response> create(@RequestBody {entity_name}Request req) {{\n")
        f.write("        return ResponseEntity.ok(service.create(req));\n")
        f.write("    }\n\n")
        
        f.write("    @PutMapping(\"/{id}\")\n")
        f.write(f"    public ResponseEntity<{entity_name}Response> update(@PathVariable Long id, @RequestBody {entity_name}RequestUpdate req) {{\n")
        f.write("        return ResponseEntity.ok(service.update(id, req));\n")
        f.write("    }\n\n")
        
        f.write("    @DeleteMapping(\"/{id}\")\n")
        f.write(f"    public ResponseEntity<{entity_name}Response> delete(@PathVariable Long id) {{\n")
        f.write("        return ResponseEntity.ok(service.delete(id));\n")
        f.write("    }\n")
        f.write("}\n")
        
    print(f"Generated {entity_name}")

if __name__ == "__main__":
    for file in os.listdir(old_models_dir):
        if file.endswith('.java'):
            res = parse_model(os.path.join(old_models_dir, file))
            if res:
                all_entities.add(res[0])
                models_info[res[0]] = res
                
    for entity_name, res in models_info.items():
        generate_feature(res[0], res[1], res[2], res[3])

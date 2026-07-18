import os
import re

admin_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\web_service\src\main\java\com\example\web_service\feature\admin"

features = [d for d in os.listdir(admin_dir) if os.path.isdir(os.path.join(admin_dir, d))]

for feature in features:
    feature_dir = os.path.join(admin_dir, feature)
    
    # Update Service
    service_dir = os.path.join(feature_dir, "service")
    if os.path.exists(service_dir):
        for f_name in os.listdir(service_dir):
            if f_name.endswith(".java"):
                f_path = os.path.join(service_dir, f_name)
                with open(f_path, 'r', encoding='utf-8') as f:
                    content = f.read()
                if "int page, int size" in content:
                    content = content.replace("int page, int size", "org.springframework.data.domain.Pageable pageable")
                    with open(f_path, 'w', encoding='utf-8') as f:
                        f.write(content)

    # Update ServiceImplementation
    impl_dir = os.path.join(feature_dir, "serviceImpl")
    if os.path.exists(impl_dir):
        for f_name in os.listdir(impl_dir):
            if f_name.endswith(".java"):
                f_path = os.path.join(impl_dir, f_name)
                with open(f_path, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                content = content.replace("getPaginated(int page, int size)", "getPaginated(org.springframework.data.domain.Pageable pageable)")
                content = content.replace("repository.findAll(PageRequest.of(page, size))", "repository.findAll(pageable)")
                
                with open(f_path, 'w', encoding='utf-8') as f:
                    f.write(content)
                    
    # Update Controller
    ctrl_dir = os.path.join(feature_dir, "controller")
    if os.path.exists(ctrl_dir):
        for f_name in os.listdir(ctrl_dir):
            if f_name.endswith(".java"):
                f_path = os.path.join(ctrl_dir, f_name)
                with open(f_path, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                # if already has the paginated endpoint with (int page, int size)
                if "@GetMapping(\"/paginated\")" in content and "int page" in content:
                    pattern = r'@GetMapping\("/paginated"\)[\s\S]*?public ResponseEntity<([^>]+)>\s*getPaginated\([\s\S]*?int size\s*\)\s*\{[\s\S]*?return ResponseEntity\.ok\(service\.getPaginated\(page,\s*size\)\);\s*\}'
                    replacement = r'@GetMapping("/paginated")\n    public ResponseEntity<\1> getPaginated(org.springframework.data.domain.Pageable pageable) {\n        return ResponseEntity.ok(service.getPaginated(pageable));\n    }'
                    content = re.sub(pattern, replacement, content)
                elif "@GetMapping(\"/paginated\")" not in content:
                    # Inject the paginated endpoint
                    entity_name = f_name.replace("Controller.java", "")
                    # find the place to inject
                    injection = f"""
    @GetMapping("/paginated")
    public ResponseEntity<{entity_name}PageResponseDto<{entity_name}Response>> getPaginated(org.springframework.data.domain.Pageable pageable) {{
        return ResponseEntity.ok(service.getPaginated(pageable));
    }}
"""
                    # Insert before @PostMapping
                    content = content.replace("@PostMapping", injection.lstrip() + "\n    @PostMapping")

                with open(f_path, 'w', encoding='utf-8') as f:
                    f.write(content)

print("Pagination refactored to use Pageable.")

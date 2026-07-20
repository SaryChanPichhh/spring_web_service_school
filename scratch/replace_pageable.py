import os
import re

base_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\web_service\src\main\java\com\example\web_service\feature\admin"

for root, dirs, files in os.walk(base_dir):
    if "controller" in root.split(os.sep):
        for file in files:
            if file.endswith("Controller.java"):
                file_path = os.path.join(root, file)
                with open(file_path, "r", encoding="utf-8") as f:
                    content = f.read()

                # Regex to match the getPaginated signature
                pattern = re.compile(
                    r'public ResponseEntity<([^>]+(?:<[^>]+>)?)>\s*getPaginated\(\s*org\.springframework\.data\.domain\.Pageable\s+pageable\s*\)\s*\{'
                )
                
                def replacement(match):
                    return (
                        f'public ResponseEntity<{match.group(1)}> getPaginated(\n'
                        f'            @RequestParam(defaultValue = "1") int page,\n'
                        f'            @RequestParam(defaultValue = "10") int size\n'
                        f'    ) {{\n'
                        f'        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page > 0 ? page - 1 : 0, size);'
                    )

                new_content = pattern.sub(replacement, content)

                if new_content != content:
                    with open(file_path, "w", encoding="utf-8") as f:
                        f.write(new_content)
                    print(f"Updated: {file_path}")

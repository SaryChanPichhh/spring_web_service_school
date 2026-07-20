$files = Get-ChildItem -Path src/main/java/com/example/web_service -Filter *Mapper.java -Recurse
foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName)
    
    # Check if we need to modify this file
    if ($content -match " _m = new ") {
        Write-Host "Modifying $($file.Name)"
        
        # Add RequiredArgsConstructor if missing
        if ($content -notmatch "import lombok.RequiredArgsConstructor;") {
            $content = $content -replace "import org.springframework.stereotype.Component;", "import org.springframework.stereotype.Component;`r`nimport lombok.RequiredArgsConstructor;"
        }
        if ($content -notmatch "@RequiredArgsConstructor") {
            $content = $content -replace "@Component", "@Component`r`n@RequiredArgsConstructor"
        }
        
        # Add EntityManager if missing
        if ($content -notmatch "jakarta.persistence.EntityManager") {
            $content = $content -replace "public class (\w+) \{", "public class `$1 {`r`n`r`n    private final jakarta.persistence.EntityManager entityManager;"
        }
        
        # Replace the mapping blocks
        $regex = "(?sm)[ \t]*if \(req\.(\w+)\(\) != null\) \{\s*([a-zA-Z0-9_\.]+) _m = new \2\(\);\s*_m\.set[a-zA-Z0-9_]+\((.*?)\);\s*entity\.set([a-zA-Z0-9_]+)\(_m\);\s*\}"
        $content = [regex]::Replace($content, $regex, {
            param($match)
            return "        if (req.$($match.Groups[1].Value)() != null) {`r`n            entity.set$($match.Groups[4].Value)(entityManager.find($($match.Groups[2].Value).class, $($match.Groups[3].Value)));`r`n        }"
        })
        
        [System.IO.File]::WriteAllText($file.FullName, $content)
    }
}

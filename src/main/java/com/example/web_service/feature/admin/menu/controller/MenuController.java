package com.example.web_service.feature.admin.menu.controller;

import com.example.web_service.feature.admin.menu.dto.req.*;
import com.example.web_service.feature.admin.menu.dto.res.*;
import com.example.web_service.feature.admin.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService service;

    @GetMapping
    public ResponseEntity<List<MenuResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<MenuPageResponseDto<MenuResponse>> getPaginated(org.springframework.data.domain.Pageable pageable) {
        return ResponseEntity.ok(service.getPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<MenuResponse> create(@RequestBody MenuRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuResponse> update(@PathVariable Long id, @RequestBody MenuRequestUpdate req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MenuResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

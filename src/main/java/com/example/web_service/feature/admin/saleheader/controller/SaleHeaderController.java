package com.example.web_service.feature.admin.saleheader.controller;

import com.example.web_service.feature.admin.saleheader.dto.req.*;
import com.example.web_service.feature.admin.saleheader.dto.res.*;
import com.example.web_service.feature.admin.saleheader.service.SaleHeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/saleheaders")
@RequiredArgsConstructor
public class SaleHeaderController {
    private final SaleHeaderService service;

    @GetMapping
    public ResponseEntity<List<SaleHeaderResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleHeaderResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<SaleHeaderPageResponseDto<SaleHeaderResponse>> getPaginated(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page > 0 ? page - 1 : 0, size);
        return ResponseEntity.ok(service.getPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<SaleHeaderResponse> create(@RequestBody SaleHeaderRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleHeaderResponse> update(@PathVariable Long id, @RequestBody SaleHeaderRequestUpdate req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaleHeaderResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

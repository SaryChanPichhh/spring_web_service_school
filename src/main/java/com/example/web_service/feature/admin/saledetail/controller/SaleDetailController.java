package com.example.web_service.feature.admin.saledetail.controller;

import com.example.web_service.feature.admin.saledetail.dto.req.*;
import com.example.web_service.feature.admin.saledetail.dto.res.*;
import com.example.web_service.feature.admin.saledetail.service.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/saledetails")
@RequiredArgsConstructor
public class SaleDetailController {
    private final SaleDetailService service;

    @GetMapping
    public ResponseEntity<List<SaleDetailResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetailResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<SaleDetailPageResponseDto<SaleDetailResponse>> getPaginated(org.springframework.data.domain.Pageable pageable) {
        return ResponseEntity.ok(service.getPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<SaleDetailResponse> create(@RequestBody SaleDetailRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetailResponse> update(@PathVariable Long id, @RequestBody SaleDetailRequestUpdate req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaleDetailResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

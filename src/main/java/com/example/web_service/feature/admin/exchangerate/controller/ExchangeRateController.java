package com.example.web_service.feature.admin.exchangerate.controller;

import com.example.web_service.feature.admin.exchangerate.dto.req.*;
import com.example.web_service.feature.admin.exchangerate.dto.res.*;
import com.example.web_service.feature.admin.exchangerate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/exchangerates")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final ExchangeRateService service;

    @GetMapping
    public ResponseEntity<List<ExchangeRateResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRateResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<ExchangeRatePageResponseDto<ExchangeRateResponse>> getPaginated(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page > 0 ? page - 1 : 0, size);
        return ResponseEntity.ok(service.getPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<ExchangeRateResponse> create(@RequestBody ExchangeRateRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExchangeRateResponse> update(@PathVariable Long id, @RequestBody ExchangeRateRequestUpdate req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExchangeRateResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

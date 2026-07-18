package com.example.web_service.feature.admin.faq.controller;

import com.example.web_service.feature.admin.faq.dto.req.*;
import com.example.web_service.feature.admin.faq.dto.res.*;
import com.example.web_service.feature.admin.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/faqs")
@RequiredArgsConstructor
public class FaqController {
    private final FaqService service;

    @GetMapping
    public ResponseEntity<List<FaqResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaqResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<FaqPageResponseDto<FaqResponse>> getPaginated(org.springframework.data.domain.Pageable pageable) {
        return ResponseEntity.ok(service.getPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<FaqResponse> create(@RequestBody FaqRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaqResponse> update(@PathVariable Long id, @RequestBody FaqRequestUpdate req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FaqResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

package com.example.web_service.feature.admin.review.controller;

import com.example.web_service.feature.admin.review.dto.req.*;
import com.example.web_service.feature.admin.review.dto.res.*;
import com.example.web_service.feature.admin.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<ReviewPageResponseDto<ReviewResponse>> getPaginated(org.springframework.data.domain.Pageable pageable) {
        return ResponseEntity.ok(service.getPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> create(@RequestBody ReviewRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable Long id, @RequestBody ReviewRequestUpdate req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

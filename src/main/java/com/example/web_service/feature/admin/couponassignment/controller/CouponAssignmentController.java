package com.example.web_service.feature.admin.couponassignment.controller;

import com.example.web_service.feature.admin.couponassignment.dto.req.*;
import com.example.web_service.feature.admin.couponassignment.dto.res.*;
import com.example.web_service.feature.admin.couponassignment.service.CouponAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/couponassignments")
@RequiredArgsConstructor
public class CouponAssignmentController {
    private final CouponAssignmentService service;

    @GetMapping
    public ResponseEntity<List<CouponAssignmentResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponAssignmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<CouponAssignmentPageResponseDto<CouponAssignmentResponse>> getPaginated(org.springframework.data.domain.Pageable pageable) {
        return ResponseEntity.ok(service.getPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<CouponAssignmentResponse> create(@RequestBody CouponAssignmentRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponAssignmentResponse> update(@PathVariable Long id, @RequestBody CouponAssignmentRequestUpdate req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CouponAssignmentResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

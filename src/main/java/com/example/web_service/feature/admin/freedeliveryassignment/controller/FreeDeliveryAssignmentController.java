package com.example.web_service.feature.admin.freedeliveryassignment.controller;

import com.example.web_service.feature.admin.freedeliveryassignment.dto.req.*;
import com.example.web_service.feature.admin.freedeliveryassignment.dto.res.*;
import com.example.web_service.feature.admin.freedeliveryassignment.service.FreeDeliveryAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/freedeliveryassignments")
@RequiredArgsConstructor
public class FreeDeliveryAssignmentController {
    private final FreeDeliveryAssignmentService service;

    @GetMapping
    public ResponseEntity<List<FreeDeliveryAssignmentResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreeDeliveryAssignmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<FreeDeliveryAssignmentPageResponseDto<FreeDeliveryAssignmentResponse>> getPaginated(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page > 0 ? page - 1 : 0, size);
        return ResponseEntity.ok(service.getPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<FreeDeliveryAssignmentResponse> create(@RequestBody FreeDeliveryAssignmentRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreeDeliveryAssignmentResponse> update(@PathVariable Long id, @RequestBody FreeDeliveryAssignmentRequestUpdate req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FreeDeliveryAssignmentResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

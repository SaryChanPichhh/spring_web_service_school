package com.example.web_service.feature.admin.couponassignment.service;

import com.example.web_service.feature.admin.couponassignment.dto.req.*;
import com.example.web_service.feature.admin.couponassignment.dto.res.*;
import java.util.List;

public interface CouponAssignmentService {
    List<CouponAssignmentResponse> getAll();
    CouponAssignmentResponse getById(Long id);
    CouponAssignmentResponse create(CouponAssignmentRequest req);
    CouponAssignmentResponse update(Long id, CouponAssignmentRequestUpdate reqUpdate);
    CouponAssignmentResponse delete(Long id);
    CouponAssignmentPageResponseDto<CouponAssignmentResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

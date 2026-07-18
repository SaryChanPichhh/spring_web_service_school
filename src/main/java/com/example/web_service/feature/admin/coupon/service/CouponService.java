package com.example.web_service.feature.admin.coupon.service;

import com.example.web_service.feature.admin.coupon.dto.req.*;
import com.example.web_service.feature.admin.coupon.dto.res.*;
import java.util.List;

public interface CouponService {
    List<CouponResponse> getAll();
    CouponResponse getById(Long id);
    CouponResponse create(CouponRequest req);
    CouponResponse update(Long id, CouponRequestUpdate reqUpdate);
    CouponResponse delete(Long id);
    CouponPageResponseDto<CouponResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

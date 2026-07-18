package com.example.web_service.feature.admin.coupon.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CouponRequestUpdate(
    String code,
    String description,
    String discountType,
    Double discountValue,
    Double minOrderAmount,
    Integer maxUsage,
    Integer usedCount,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String status
) {
}

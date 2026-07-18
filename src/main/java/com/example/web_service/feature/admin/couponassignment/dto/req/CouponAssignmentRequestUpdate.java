package com.example.web_service.feature.admin.couponassignment.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.coupon.model.Coupon;
import com.example.web_service.feature.admin.menu.model.Menu;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;

public record CouponAssignmentRequestUpdate(
    Coupon coupon,
    Restaurant restaurant,
    Menu menuItem,
    String assignmentType,
    String notes,
    String status
) {
}

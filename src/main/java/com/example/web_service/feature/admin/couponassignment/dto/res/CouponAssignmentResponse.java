package com.example.web_service.feature.admin.couponassignment.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.coupon.model.Coupon;
import com.example.web_service.feature.admin.menu.model.Menu;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;

@Getter
@Setter
public class CouponAssignmentResponse {
    private Long assignmentId;
    private Coupon coupon;
    private Restaurant restaurant;
    private Menu menuItem;
    private String assignmentType;
    private String notes;
    private String status;
}

package com.example.web_service.feature.admin.review.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.admin.user.model.User;

public record ReviewRequest(
    Integer userId,
    Integer restaurantId,
    Integer rating,
    String comment
) {
}

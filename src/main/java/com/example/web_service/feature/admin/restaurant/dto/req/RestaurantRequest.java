package com.example.web_service.feature.admin.restaurant.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;

public record RestaurantRequest(
    String resName,
    String description,
    String address,
    LocalTime openTime,
    LocalTime closeTime,
    String latLng,
    Double rating,
    String avgEstimateTime,
    String imageUrl,
    Boolean isOpen,
    String basedCountry,
    Double commissionRate,
    User user
) {
}

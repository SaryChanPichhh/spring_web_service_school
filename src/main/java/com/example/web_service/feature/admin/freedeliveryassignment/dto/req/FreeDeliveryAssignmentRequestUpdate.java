package com.example.web_service.feature.admin.freedeliveryassignment.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.menu.model.Menu;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;

public record FreeDeliveryAssignmentRequestUpdate(
    Integer restaurantId,
    Integer menuItemId,
    String assignmentType,
    String status,
    Double minOrderAmount,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String notes
) {
}

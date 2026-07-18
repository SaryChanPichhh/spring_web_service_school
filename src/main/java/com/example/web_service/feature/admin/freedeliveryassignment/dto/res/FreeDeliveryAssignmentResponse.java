package com.example.web_service.feature.admin.freedeliveryassignment.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.menu.model.Menu;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;

@Getter
@Setter
public class FreeDeliveryAssignmentResponse {
    private Long id;
    private Restaurant restaurant;
    private Menu menuItem;
    private String assignmentType;
    private String status;
    private Double minOrderAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String notes;
}

package com.example.web_service.feature.admin.menu.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.category.model.Category;

public record MenuRequest(
    String name,
    Double price,
    String description,
    String image,
    Double rating,
    Integer restaurantsId,
    Integer categoriesId
) {
}

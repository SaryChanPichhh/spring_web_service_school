package com.example.web_service.feature.admin.favorites.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.menu.model.Menu;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.admin.user.model.User;

public record FavoritesRequestUpdate(
    Boolean status,
    User user,
    Restaurant restaurants,
    Menu menu
) {
}

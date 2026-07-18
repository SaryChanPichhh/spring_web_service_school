package com.example.web_service.feature.admin.favorites.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.menu.model.Menu;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.admin.user.model.User;

@Getter
@Setter
public class FavoritesResponse {
    private Integer id;
    private Boolean status;
    private User user;
    private Restaurant restaurants;
    private Menu menu;
}

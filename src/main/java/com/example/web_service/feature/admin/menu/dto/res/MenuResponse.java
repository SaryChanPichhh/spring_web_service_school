package com.example.web_service.feature.admin.menu.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;

@Getter
@Setter
public class MenuResponse {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private String image;
    private Double rating;
    private Restaurant restaurants;
}

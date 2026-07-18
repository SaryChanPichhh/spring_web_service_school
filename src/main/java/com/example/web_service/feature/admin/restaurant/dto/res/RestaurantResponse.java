package com.example.web_service.feature.admin.restaurant.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;

@Getter
@Setter
public class RestaurantResponse {
    private Integer resId;
    private String resName;
    private String description;
    private String address;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String latLng;
    private Double rating;
    private String avgEstimateTime;
    private String imageUrl;
    private Boolean isOpen;
    private String basedCountry;
    private Double commissionRate;
    private User user;
}

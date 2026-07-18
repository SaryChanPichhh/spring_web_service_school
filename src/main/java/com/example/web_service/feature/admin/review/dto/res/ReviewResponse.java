package com.example.web_service.feature.admin.review.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.admin.user.model.User;

@Getter
@Setter
public class ReviewResponse {
    private Long id;
    private User user;
    private Restaurant restaurant;
    private Integer rating;
    private String comment;
}

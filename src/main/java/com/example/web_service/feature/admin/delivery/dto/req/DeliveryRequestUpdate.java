package com.example.web_service.feature.admin.delivery.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;

public record DeliveryRequestUpdate(
    String name,
    String email,
    String phone,
    String address,
    Boolean status,
    String imageUrl,
    String driverLicense,
    String nationalIdUrl,
    String driverLicenseUrl,
    Integer rating,
    String city,
    String state,
    String zip,
    String country,
    Integer usersId
) {
}

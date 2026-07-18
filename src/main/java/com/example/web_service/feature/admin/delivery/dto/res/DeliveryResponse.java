package com.example.web_service.feature.admin.delivery.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;

@Getter
@Setter
public class DeliveryResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean status;
    private String imageUrl;
    private String nationalId;
    private String driverLicense;
    private String nationalIdUrl;
    private String driverLicenseUrl;
    private Integer rating;
    private String city;
    private String state;
    private String zip;
    private String country;
    private User users;
}

package com.example.web_service.feature.admin.user.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.permission.model.Permission;
import java.util.List;

@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String deviceId;
    private String address;
    private String password;
    private String role;
    private String profileImage;
    private Boolean isActive;
    private List<Permission> permissions;
}

package com.example.web_service.feature.admin.user.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.permission.model.Permission;
import java.util.List;

public record UserRequestUpdate(
    String userName,
    String firstName,
    String lastName,
    String email,
    String phone,
    String address,
    String password,
    String role,
    String profileImage,
    Boolean isActive,
    List<Permission> permissions
) {
}

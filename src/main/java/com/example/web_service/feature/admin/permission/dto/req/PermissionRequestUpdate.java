package com.example.web_service.feature.admin.permission.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;
import java.util.List;

public record PermissionRequestUpdate(
    String parentName,
    String childName,
    String description,
    List<User> users
) {
}

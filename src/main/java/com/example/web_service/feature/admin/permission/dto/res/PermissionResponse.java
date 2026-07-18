package com.example.web_service.feature.admin.permission.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;
import java.util.List;

@Getter
@Setter
public class PermissionResponse {
    private Integer permissionId;
    private String parentName;
    private String childName;
    private String description;
    private List<User> users;
}

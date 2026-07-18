package com.example.web_service.feature.admin.permission.model;

import com.example.web_service.shared.BasedEntity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "permissions")
public class Permission extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionId;
    private String parentName;
    private String childName;
    private String description;
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private List<User> users;
}

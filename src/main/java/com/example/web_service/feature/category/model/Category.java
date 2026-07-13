package com.example.web_service.feature.category.model;

import com.example.web_service.shared.BasedEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
public class Category extends BasedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Column(nullable = false, unique = true)
    private String title;

    @NotBlank(message = "Slug is required")
    @Size(min = 2, max = 100, message = "Slug must be between 2 and 100 characters")
    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = true)
    private String imageUrl;

    private Integer sortOrder = 0;

    @NotNull(message = "isActive is required")
    @Column(nullable = false)
    private Boolean isActive = false;

    @Nullable
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}

package com.example.web_service.feature.category.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryRequestUpdate(
        @NotNull
        Long id,
    Long parentId,

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    String title,

    @NotBlank(message = "Slug is required")
    @Size(min = 2, max = 100, message = "Slug must be between 2 and 100 characters")
    String slug,

    String imageUrl,

    Integer sortOrder,

    @NotNull(message = "isActive is required")
    Boolean isActive,

    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description
) {
}

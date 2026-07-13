package com.example.web_service.feature.category.dto.res;

public record CategoryResponse(
    Long id,
    Long parentId,
    String title,
    String slug,
    String imageUrl,
    Integer sortOrder,
    Boolean isActive,
    String description
) {
}

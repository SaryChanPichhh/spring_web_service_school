package com.example.web_service.feature.category.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private Long parentId;
    private String title;
    private String slug;
    private String imageUrl;
    private Integer sortOrder;
    private Boolean isActive;
    private String description;
}

package com.example.web_service.feature.category.mapper;

import com.example.web_service.feature.category.dto.req.CategoryRequest;
import com.example.web_service.feature.category.dto.req.CategoryRequestUpdate;
import com.example.web_service.feature.category.dto.res.CategoryResponse;
import com.example.web_service.feature.category.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category fromRequest(CategoryRequest categoryRequest);
    
    CategoryResponse toResponse(Category category);
    
    java.util.List<CategoryResponse> toResponseList(java.util.List<Category> categories);
    
    void updateFromRequest(@MappingTarget Category category, CategoryRequestUpdate categoryRequestUpdate);
}

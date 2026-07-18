package com.example.web_service.feature.category.service;

import com.example.web_service.feature.category.dto.req.CategoryRequest;
import com.example.web_service.feature.category.dto.req.CategoryRequestUpdate;
import com.example.web_service.feature.category.dto.res.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long id);

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(Long id, CategoryRequestUpdate categoryRequestUpdate);

    CategoryResponse deleteCategory(Long id);

    com.example.web_service.feature.category.dto.res.CategoryPageResponseDto<CategoryResponse> getCategoriesPaginated(org.springframework.data.domain.Pageable pageable);
}

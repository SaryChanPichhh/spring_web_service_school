package com.example.web_service.feature.category.controller;

import com.example.web_service.feature.category.dto.req.CategoryRequest;
import com.example.web_service.feature.category.dto.req.CategoryRequestUpdate;
import com.example.web_service.feature.category.dto.res.CategoryResponse;
import com.example.web_service.feature.category.service.CategoryService;
import com.example.web_service.shared.ApiResponse;
import com.example.web_service.shared.ApiRoutes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(
                ApiResponse.<List<CategoryResponse>>builder().data(categories).message("Categories retrieved successfully").statusCode(200).build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable Long id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(
                ApiResponse.<CategoryResponse>builder().data(category).message("Category retrieved successfully").statusCode(200).build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse created = categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok(
                ApiResponse.<CategoryResponse>builder().data(created).message("Category created successfully").statusCode(201).build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestUpdate categoryRequestUpdate) {
        CategoryResponse updated = categoryService.updateCategory(id, categoryRequestUpdate);
        return ResponseEntity.ok(
                ApiResponse.<CategoryResponse>builder().data(updated).message("Category updated successfully").statusCode(200).build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> deleteCategory(@PathVariable Long id) {
        CategoryResponse deleted = categoryService.deleteCategory(id);
        return ResponseEntity.ok(
                ApiResponse.<CategoryResponse>builder().data(deleted).message("Category deleted successfully").statusCode(200).build()
        );
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<com.example.web_service.feature.category.dto.res.CategoryPageResponseDto<CategoryResponse>>> getCategoriesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size);
        com.example.web_service.feature.category.dto.res.CategoryPageResponseDto<CategoryResponse> pagedCategories = categoryService.getCategoriesPaginated(pageable);
        return ResponseEntity.ok(
                ApiResponse.<com.example.web_service.feature.category.dto.res.CategoryPageResponseDto<CategoryResponse>>builder()
                        .data(pagedCategories)
                        .message("Categories paginated successfully")
                        .statusCode(200)
                        .build()
        );
    }
}

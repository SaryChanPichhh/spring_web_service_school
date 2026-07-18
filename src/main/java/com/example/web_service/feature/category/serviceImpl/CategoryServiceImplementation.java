package com.example.web_service.feature.category.serviceImpl;

import com.example.web_service.feature.category.dto.req.CategoryRequest;
import com.example.web_service.feature.category.dto.req.CategoryRequestUpdate;
import com.example.web_service.feature.category.dto.res.CategoryResponse;
import com.example.web_service.feature.category.mapper.CategoryMapper;
import com.example.web_service.feature.category.model.Category;
import com.example.web_service.feature.category.repository.CategoryRepository;
import com.example.web_service.feature.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toResponseList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryMapper.toResponse(findCategoryById(id));
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.fromRequest(categoryRequest);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequestUpdate categoryRequestUpdate) {
        Category existingCategory = findCategoryById(id);
        categoryMapper.updateFromRequest(existingCategory, categoryRequestUpdate);
        Category savedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public CategoryResponse deleteCategory(Long id) {
        Category existingCategory = findCategoryById(id);
        categoryRepository.delete(existingCategory);
        return categoryMapper.toResponse(existingCategory);
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category not found with id: " + id));
    }

    @Override
    public com.example.web_service.feature.category.dto.res.CategoryPageResponseDto<CategoryResponse> getCategoriesPaginated(org.springframework.data.domain.Pageable pageable) {
        org.springframework.data.domain.Page<Category> categoryPage = categoryRepository.findAll(pageable);
        
        com.example.web_service.feature.category.dto.res.CategoryPageResponseDto<CategoryResponse> pageResponse = new com.example.web_service.feature.category.dto.res.CategoryPageResponseDto<>();
        pageResponse.setContent(categoryMapper.toResponseList(categoryPage.getContent()));
        pageResponse.setTotalElement((int) categoryPage.getTotalElements());
        pageResponse.setTotalPages(categoryPage.getTotalPages());
        pageResponse.setLast(categoryPage.isLast());
        pageResponse.setFirst(categoryPage.isFirst());
        
        return pageResponse;
    }
}


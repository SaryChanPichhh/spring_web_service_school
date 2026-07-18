package com.example.web_service.feature.admin.review.service;

import com.example.web_service.feature.admin.review.dto.req.*;
import com.example.web_service.feature.admin.review.dto.res.*;
import java.util.List;

public interface ReviewService {
    List<ReviewResponse> getAll();
    ReviewResponse getById(Long id);
    ReviewResponse create(ReviewRequest req);
    ReviewResponse update(Long id, ReviewRequestUpdate reqUpdate);
    ReviewResponse delete(Long id);
    ReviewPageResponseDto<ReviewResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

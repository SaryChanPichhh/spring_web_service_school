package com.example.web_service.feature.admin.feedback.service;

import com.example.web_service.feature.admin.feedback.dto.req.*;
import com.example.web_service.feature.admin.feedback.dto.res.*;
import java.util.List;

public interface FeedbackService {
    List<FeedbackResponse> getAll();
    FeedbackResponse getById(Long id);
    FeedbackResponse create(FeedbackRequest req);
    FeedbackResponse update(Long id, FeedbackRequestUpdate reqUpdate);
    FeedbackResponse delete(Long id);
    FeedbackPageResponseDto<FeedbackResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

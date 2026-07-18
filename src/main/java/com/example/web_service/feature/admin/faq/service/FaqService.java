package com.example.web_service.feature.admin.faq.service;

import com.example.web_service.feature.admin.faq.dto.req.*;
import com.example.web_service.feature.admin.faq.dto.res.*;
import java.util.List;

public interface FaqService {
    List<FaqResponse> getAll();
    FaqResponse getById(Long id);
    FaqResponse create(FaqRequest req);
    FaqResponse update(Long id, FaqRequestUpdate reqUpdate);
    FaqResponse delete(Long id);
    FaqPageResponseDto<FaqResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

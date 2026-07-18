package com.example.web_service.feature.admin.exchangerate.service;

import com.example.web_service.feature.admin.exchangerate.dto.req.*;
import com.example.web_service.feature.admin.exchangerate.dto.res.*;
import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRateResponse> getAll();
    ExchangeRateResponse getById(Long id);
    ExchangeRateResponse create(ExchangeRateRequest req);
    ExchangeRateResponse update(Long id, ExchangeRateRequestUpdate reqUpdate);
    ExchangeRateResponse delete(Long id);
    ExchangeRatePageResponseDto<ExchangeRateResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

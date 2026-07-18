package com.example.web_service.feature.admin.saleheader.service;

import com.example.web_service.feature.admin.saleheader.dto.req.*;
import com.example.web_service.feature.admin.saleheader.dto.res.*;
import java.util.List;

public interface SaleHeaderService {
    List<SaleHeaderResponse> getAll();
    SaleHeaderResponse getById(Long id);
    SaleHeaderResponse create(SaleHeaderRequest req);
    SaleHeaderResponse update(Long id, SaleHeaderRequestUpdate reqUpdate);
    SaleHeaderResponse delete(Long id);
    SaleHeaderPageResponseDto<SaleHeaderResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

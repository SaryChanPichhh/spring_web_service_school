package com.example.web_service.feature.admin.saledetail.service;

import com.example.web_service.feature.admin.saledetail.dto.req.*;
import com.example.web_service.feature.admin.saledetail.dto.res.*;
import java.util.List;

public interface SaleDetailService {
    List<SaleDetailResponse> getAll();
    SaleDetailResponse getById(Long id);
    SaleDetailResponse create(SaleDetailRequest req);
    SaleDetailResponse update(Long id, SaleDetailRequestUpdate reqUpdate);
    SaleDetailResponse delete(Long id);
    SaleDetailPageResponseDto<SaleDetailResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

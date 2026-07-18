package com.example.web_service.feature.admin.delivery.service;

import com.example.web_service.feature.admin.delivery.dto.req.*;
import com.example.web_service.feature.admin.delivery.dto.res.*;
import java.util.List;

public interface DeliveryService {
    List<DeliveryResponse> getAll();
    DeliveryResponse getById(Long id);
    DeliveryResponse create(DeliveryRequest req);
    DeliveryResponse update(Long id, DeliveryRequestUpdate reqUpdate);
    DeliveryResponse delete(Long id);
    DeliveryPageResponseDto<DeliveryResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

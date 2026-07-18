package com.example.web_service.feature.admin.freedeliveryassignment.service;

import com.example.web_service.feature.admin.freedeliveryassignment.dto.req.*;
import com.example.web_service.feature.admin.freedeliveryassignment.dto.res.*;
import java.util.List;

public interface FreeDeliveryAssignmentService {
    List<FreeDeliveryAssignmentResponse> getAll();
    FreeDeliveryAssignmentResponse getById(Long id);
    FreeDeliveryAssignmentResponse create(FreeDeliveryAssignmentRequest req);
    FreeDeliveryAssignmentResponse update(Long id, FreeDeliveryAssignmentRequestUpdate reqUpdate);
    FreeDeliveryAssignmentResponse delete(Long id);
    FreeDeliveryAssignmentPageResponseDto<FreeDeliveryAssignmentResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

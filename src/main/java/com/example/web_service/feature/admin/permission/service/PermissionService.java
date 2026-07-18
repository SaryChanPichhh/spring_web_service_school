package com.example.web_service.feature.admin.permission.service;

import com.example.web_service.feature.admin.permission.dto.req.*;
import com.example.web_service.feature.admin.permission.dto.res.*;
import java.util.List;

public interface PermissionService {
    List<PermissionResponse> getAll();
    PermissionResponse getById(Long id);
    PermissionResponse create(PermissionRequest req);
    PermissionResponse update(Long id, PermissionRequestUpdate reqUpdate);
    PermissionResponse delete(Long id);
    PermissionPageResponseDto<PermissionResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

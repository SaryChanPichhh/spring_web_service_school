package com.example.web_service.feature.admin.menu.service;

import com.example.web_service.feature.admin.menu.dto.req.*;
import com.example.web_service.feature.admin.menu.dto.res.*;
import java.util.List;

public interface MenuService {
    List<MenuResponse> getAll();
    MenuResponse getById(Long id);
    MenuResponse create(MenuRequest req);
    MenuResponse update(Long id, MenuRequestUpdate reqUpdate);
    MenuResponse delete(Long id);
    MenuPageResponseDto<MenuResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

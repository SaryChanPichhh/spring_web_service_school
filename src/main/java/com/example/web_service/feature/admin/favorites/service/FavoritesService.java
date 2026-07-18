package com.example.web_service.feature.admin.favorites.service;

import com.example.web_service.feature.admin.favorites.dto.req.*;
import com.example.web_service.feature.admin.favorites.dto.res.*;
import java.util.List;

public interface FavoritesService {
    List<FavoritesResponse> getAll();
    FavoritesResponse getById(Long id);
    FavoritesResponse create(FavoritesRequest req);
    FavoritesResponse update(Long id, FavoritesRequestUpdate reqUpdate);
    FavoritesResponse delete(Long id);
    FavoritesPageResponseDto<FavoritesResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

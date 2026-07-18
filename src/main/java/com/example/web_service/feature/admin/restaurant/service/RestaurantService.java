package com.example.web_service.feature.admin.restaurant.service;

import com.example.web_service.feature.admin.restaurant.dto.req.*;
import com.example.web_service.feature.admin.restaurant.dto.res.*;
import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getAll();
    RestaurantResponse getById(Long id);
    RestaurantResponse create(RestaurantRequest req);
    RestaurantResponse update(Long id, RestaurantRequestUpdate reqUpdate);
    RestaurantResponse delete(Long id);
    RestaurantPageResponseDto<RestaurantResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

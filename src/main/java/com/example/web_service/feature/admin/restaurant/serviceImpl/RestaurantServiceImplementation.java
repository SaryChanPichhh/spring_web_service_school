package com.example.web_service.feature.admin.restaurant.serviceImpl;

import com.example.web_service.feature.admin.restaurant.dto.req.*;
import com.example.web_service.feature.admin.restaurant.dto.res.*;
import com.example.web_service.feature.admin.restaurant.mapper.RestaurantMapper;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.admin.restaurant.repository.RestaurantRepository;
import com.example.web_service.feature.admin.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImplementation implements RestaurantService {
    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;

    @Override
    public List<RestaurantResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public RestaurantResponse getById(Long id) {
        Integer searchId = (Integer) (Object) id;
        Restaurant entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public RestaurantResponse create(RestaurantRequest req) {
        Restaurant entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public RestaurantResponse update(Long id, RestaurantRequestUpdate reqUpdate) {
        Integer searchId = (Integer) (Object) id;
        Restaurant entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public RestaurantResponse delete(Long id) {
        Integer searchId = (Integer) (Object) id;
        Restaurant entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public RestaurantPageResponseDto<RestaurantResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<Restaurant> pageResult = repository.findAll(pageable);
        RestaurantPageResponseDto<RestaurantResponse> response = new RestaurantPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

package com.example.web_service.feature.admin.favorites.serviceImpl;

import com.example.web_service.feature.admin.favorites.dto.req.*;
import com.example.web_service.feature.admin.favorites.dto.res.*;
import com.example.web_service.feature.admin.favorites.mapper.FavoritesMapper;
import com.example.web_service.feature.admin.favorites.model.Favorites;
import com.example.web_service.feature.admin.favorites.repository.FavoritesRepository;
import com.example.web_service.feature.admin.favorites.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesServiceImplementation implements FavoritesService {
    private final FavoritesRepository repository;
    private final FavoritesMapper mapper;

    @Override
    public List<FavoritesResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public FavoritesResponse getById(Long id) {
        Integer searchId = (Integer) (Object) id;
        Favorites entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public FavoritesResponse create(FavoritesRequest req) {
        Favorites entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public FavoritesResponse update(Long id, FavoritesRequestUpdate reqUpdate) {
        Integer searchId = (Integer) (Object) id;
        Favorites entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public FavoritesResponse delete(Long id) {
        Integer searchId = (Integer) (Object) id;
        Favorites entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public FavoritesPageResponseDto<FavoritesResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<Favorites> pageResult = repository.findAll(pageable);
        FavoritesPageResponseDto<FavoritesResponse> response = new FavoritesPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

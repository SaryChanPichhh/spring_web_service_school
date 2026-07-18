package com.example.web_service.feature.admin.menu.serviceImpl;

import com.example.web_service.feature.admin.menu.dto.req.*;
import com.example.web_service.feature.admin.menu.dto.res.*;
import com.example.web_service.feature.admin.menu.mapper.MenuMapper;
import com.example.web_service.feature.admin.menu.model.Menu;
import com.example.web_service.feature.admin.menu.repository.MenuRepository;
import com.example.web_service.feature.admin.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImplementation implements MenuService {
    private final MenuRepository repository;
    private final MenuMapper mapper;

    @Override
    public List<MenuResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public MenuResponse getById(Long id) {
        Integer searchId = (Integer) (Object) id;
        Menu entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public MenuResponse create(MenuRequest req) {
        Menu entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public MenuResponse update(Long id, MenuRequestUpdate reqUpdate) {
        Integer searchId = (Integer) (Object) id;
        Menu entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public MenuResponse delete(Long id) {
        Integer searchId = (Integer) (Object) id;
        Menu entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public MenuPageResponseDto<MenuResponse> getPaginated(Pageable pageable) {
        Page<Menu> pageResult = repository.findAll(pageable);
        MenuPageResponseDto<MenuResponse> response = new MenuPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

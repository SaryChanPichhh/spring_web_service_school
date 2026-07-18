package com.example.web_service.feature.admin.permission.serviceImpl;

import com.example.web_service.feature.admin.permission.dto.req.*;
import com.example.web_service.feature.admin.permission.dto.res.*;
import com.example.web_service.feature.admin.permission.mapper.PermissionMapper;
import com.example.web_service.feature.admin.permission.model.Permission;
import com.example.web_service.feature.admin.permission.repository.PermissionRepository;
import com.example.web_service.feature.admin.permission.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImplementation implements PermissionService {
    private final PermissionRepository repository;
    private final PermissionMapper mapper;

    @Override
    public List<PermissionResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public PermissionResponse getById(Long id) {
        Integer searchId = (Integer) (Object) id;
        Permission entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public PermissionResponse create(PermissionRequest req) {
        Permission entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public PermissionResponse update(Long id, PermissionRequestUpdate reqUpdate) {
        Integer searchId = (Integer) (Object) id;
        Permission entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public PermissionResponse delete(Long id) {
        Integer searchId = (Integer) (Object) id;
        Permission entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public PermissionPageResponseDto<PermissionResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<Permission> pageResult = repository.findAll(pageable);
        PermissionPageResponseDto<PermissionResponse> response = new PermissionPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

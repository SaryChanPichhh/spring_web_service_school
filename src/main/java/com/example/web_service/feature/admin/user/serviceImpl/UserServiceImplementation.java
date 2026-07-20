package com.example.web_service.feature.admin.user.serviceImpl;

import com.example.web_service.feature.admin.user.dto.req.*;
import com.example.web_service.feature.admin.user.dto.res.*;
import com.example.web_service.feature.admin.user.mapper.UserMapper;
import com.example.web_service.feature.admin.user.model.User;
import com.example.web_service.feature.admin.user.repository.UserRepository;
import com.example.web_service.feature.admin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public List<UserResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public UserResponse getById(Long id) {
        Integer searchId = id.intValue();
        User entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public UserResponse create(UserRequest req) {
        User entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public UserResponse update(Long id, UserRequestUpdate reqUpdate) {
        Integer searchId = id.intValue();
        User entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public UserResponse delete(Long id) {
        Integer searchId = id.intValue();
        User entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public UserPageResponseDto<UserResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<User> pageResult = repository.findAll(pageable);
        UserPageResponseDto<UserResponse> response = new UserPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

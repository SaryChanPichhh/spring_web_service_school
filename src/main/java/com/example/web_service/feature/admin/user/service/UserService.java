package com.example.web_service.feature.admin.user.service;

import com.example.web_service.feature.admin.user.dto.req.*;
import com.example.web_service.feature.admin.user.dto.res.*;
import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse getById(Long id);
    UserResponse create(UserRequest req);
    UserResponse update(Long id, UserRequestUpdate reqUpdate);
    UserResponse delete(Long id);
    UserPageResponseDto<UserResponse> getPaginated(org.springframework.data.domain.Pageable pageable);
}

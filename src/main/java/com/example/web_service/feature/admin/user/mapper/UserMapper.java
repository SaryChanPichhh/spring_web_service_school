package com.example.web_service.feature.admin.user.mapper;

import com.example.web_service.feature.admin.user.dto.req.*;
import com.example.web_service.feature.admin.user.dto.res.*;
import com.example.web_service.feature.admin.user.model.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User fromRequest(UserRequest req) {
        if (req == null) return null;
        User entity = new User();
        entity.setUserName(req.userName());
        entity.setFirstName(req.firstName());
        entity.setLastName(req.lastName());
        entity.setEmail(req.email());
        entity.setPhone(req.phone());
        entity.setAddress(req.address());
        entity.setPassword(req.password());
        entity.setRole(req.role());
        entity.setProfileImage(req.profileImage());
        entity.setIsActive(req.isActive());
        entity.setPermissions(req.permissions());
        return entity;
    }

    public UserResponse toResponse(User entity) {
        if (entity == null) return null;
        UserResponse res = new UserResponse();
        res.setId(entity.getId());
        res.setUserName(entity.getUserName());
        res.setFirstName(entity.getFirstName());
        res.setLastName(entity.getLastName());
        res.setEmail(entity.getEmail());
        res.setPhone(entity.getPhone());
        res.setDeviceId(entity.getDeviceId());
        res.setAddress(entity.getAddress());
        res.setPassword(entity.getPassword());
        res.setRole(entity.getRole());
        res.setProfileImage(entity.getProfileImage());
        res.setIsActive(entity.getIsActive());
        res.setPermissions(entity.getPermissions());
        return res;
    }

    public List<UserResponse> toResponseList(List<User> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(User entity, UserRequestUpdate req) {
        if (req == null) return;
        if (req.userName() != null) {
            entity.setUserName(req.userName());
        }
        if (req.firstName() != null) {
            entity.setFirstName(req.firstName());
        }
        if (req.lastName() != null) {
            entity.setLastName(req.lastName());
        }
        if (req.email() != null) {
            entity.setEmail(req.email());
        }
        if (req.phone() != null) {
            entity.setPhone(req.phone());
        }
        if (req.address() != null) {
            entity.setAddress(req.address());
        }
        if (req.password() != null) {
            entity.setPassword(req.password());
        }
        if (req.role() != null) {
            entity.setRole(req.role());
        }
        if (req.profileImage() != null) {
            entity.setProfileImage(req.profileImage());
        }
        if (req.isActive() != null) {
            entity.setIsActive(req.isActive());
        }
        if (req.permissions() != null) {
            entity.setPermissions(req.permissions());
        }
    }
}

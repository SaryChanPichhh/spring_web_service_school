package com.example.web_service.feature.admin.permission.mapper;

import com.example.web_service.feature.admin.permission.dto.req.*;
import com.example.web_service.feature.admin.permission.dto.res.*;
import com.example.web_service.feature.admin.permission.model.Permission;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {

    public Permission fromRequest(PermissionRequest req) {
        if (req == null) return null;
        Permission entity = new Permission();
        entity.setParentName(req.parentName());
        entity.setChildName(req.childName());
        entity.setDescription(req.description());
        entity.setUsers(req.users());
        return entity;
    }

    public PermissionResponse toResponse(Permission entity) {
        if (entity == null) return null;
        PermissionResponse res = new PermissionResponse();
        res.setPermissionId(entity.getPermissionId());
        res.setParentName(entity.getParentName());
        res.setChildName(entity.getChildName());
        res.setDescription(entity.getDescription());
        res.setUsers(entity.getUsers());
        return res;
    }

    public List<PermissionResponse> toResponseList(List<Permission> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(Permission entity, PermissionRequestUpdate req) {
        if (req == null) return;
        if (req.parentName() != null) {
            entity.setParentName(req.parentName());
        }
        if (req.childName() != null) {
            entity.setChildName(req.childName());
        }
        if (req.description() != null) {
            entity.setDescription(req.description());
        }
        if (req.users() != null) {
            entity.setUsers(req.users());
        }
    }
}

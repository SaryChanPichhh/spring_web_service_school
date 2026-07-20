package com.example.web_service.feature.admin.freedeliveryassignment.mapper;

import com.example.web_service.feature.admin.freedeliveryassignment.dto.req.*;
import com.example.web_service.feature.admin.freedeliveryassignment.dto.res.*;
import com.example.web_service.feature.admin.freedeliveryassignment.model.FreeDeliveryAssignment;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FreeDeliveryAssignmentMapper {

    private final jakarta.persistence.EntityManager entityManager;

    public FreeDeliveryAssignment fromRequest(FreeDeliveryAssignmentRequest req) {
        if (req == null) return null;
        FreeDeliveryAssignment entity = new FreeDeliveryAssignment();
        if (req.restaurantId() != null) {
            entity.setRestaurant(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantId()));
        }
        if (req.menuItemId() != null) {
            entity.setMenuItem(entityManager.find(com.example.web_service.feature.admin.menu.model.Menu.class, req.menuItemId()));
        }
        entity.setAssignmentType(req.assignmentType());
        entity.setStatus(req.status());
        entity.setMinOrderAmount(req.minOrderAmount());
        entity.setStartDate(req.startDate());
        entity.setEndDate(req.endDate());
        entity.setNotes(req.notes());
        return entity;
    }

    public FreeDeliveryAssignmentResponse toResponse(FreeDeliveryAssignment entity) {
        if (entity == null) return null;
        FreeDeliveryAssignmentResponse res = new FreeDeliveryAssignmentResponse();
        res.setId(entity.getId());
        res.setRestaurant(entity.getRestaurant());
        res.setMenuItem(entity.getMenuItem());
        res.setAssignmentType(entity.getAssignmentType());
        res.setStatus(entity.getStatus());
        res.setMinOrderAmount(entity.getMinOrderAmount());
        res.setStartDate(entity.getStartDate());
        res.setEndDate(entity.getEndDate());
        res.setNotes(entity.getNotes());
        return res;
    }

    public List<FreeDeliveryAssignmentResponse> toResponseList(List<FreeDeliveryAssignment> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(FreeDeliveryAssignment entity, FreeDeliveryAssignmentRequestUpdate req) {
        if (req == null) return;
        if (req.restaurantId() != null) {
            entity.setRestaurant(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantId()));
        }
        if (req.menuItemId() != null) {
            entity.setMenuItem(entityManager.find(com.example.web_service.feature.admin.menu.model.Menu.class, req.menuItemId()));
        }
        if (req.assignmentType() != null) {
            entity.setAssignmentType(req.assignmentType());
        }
        if (req.status() != null) {
            entity.setStatus(req.status());
        }
        if (req.minOrderAmount() != null) {
            entity.setMinOrderAmount(req.minOrderAmount());
        }
        if (req.startDate() != null) {
            entity.setStartDate(req.startDate());
        }
        if (req.endDate() != null) {
            entity.setEndDate(req.endDate());
        }
        if (req.notes() != null) {
            entity.setNotes(req.notes());
        }
    }
}

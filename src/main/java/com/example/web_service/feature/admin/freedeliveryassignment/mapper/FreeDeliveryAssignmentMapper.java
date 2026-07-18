package com.example.web_service.feature.admin.freedeliveryassignment.mapper;

import com.example.web_service.feature.admin.freedeliveryassignment.dto.req.*;
import com.example.web_service.feature.admin.freedeliveryassignment.dto.res.*;
import com.example.web_service.feature.admin.freedeliveryassignment.model.FreeDeliveryAssignment;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FreeDeliveryAssignmentMapper {

    public FreeDeliveryAssignment fromRequest(FreeDeliveryAssignmentRequest req) {
        if (req == null) return null;
        FreeDeliveryAssignment entity = new FreeDeliveryAssignment();
        entity.setRestaurant(req.restaurant());
        entity.setMenuItem(req.menuItem());
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
        if (req.restaurant() != null) {
            entity.setRestaurant(req.restaurant());
        }
        if (req.menuItem() != null) {
            entity.setMenuItem(req.menuItem());
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

package com.example.web_service.feature.admin.couponassignment.mapper;

import com.example.web_service.feature.admin.couponassignment.dto.req.*;
import com.example.web_service.feature.admin.couponassignment.dto.res.*;
import com.example.web_service.feature.admin.couponassignment.model.CouponAssignment;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CouponAssignmentMapper {

    public CouponAssignment fromRequest(CouponAssignmentRequest req) {
        if (req == null) return null;
        CouponAssignment entity = new CouponAssignment();
        entity.setCoupon(req.coupon());
        entity.setRestaurant(req.restaurant());
        entity.setMenuItem(req.menuItem());
        entity.setAssignmentType(req.assignmentType());
        entity.setNotes(req.notes());
        entity.setStatus(req.status());
        return entity;
    }

    public CouponAssignmentResponse toResponse(CouponAssignment entity) {
        if (entity == null) return null;
        CouponAssignmentResponse res = new CouponAssignmentResponse();
        res.setAssignmentId(entity.getAssignmentId());
        res.setCoupon(entity.getCoupon());
        res.setRestaurant(entity.getRestaurant());
        res.setMenuItem(entity.getMenuItem());
        res.setAssignmentType(entity.getAssignmentType());
        res.setNotes(entity.getNotes());
        res.setStatus(entity.getStatus());
        return res;
    }

    public List<CouponAssignmentResponse> toResponseList(List<CouponAssignment> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(CouponAssignment entity, CouponAssignmentRequestUpdate req) {
        if (req == null) return;
        if (req.coupon() != null) {
            entity.setCoupon(req.coupon());
        }
        if (req.restaurant() != null) {
            entity.setRestaurant(req.restaurant());
        }
        if (req.menuItem() != null) {
            entity.setMenuItem(req.menuItem());
        }
        if (req.assignmentType() != null) {
            entity.setAssignmentType(req.assignmentType());
        }
        if (req.notes() != null) {
            entity.setNotes(req.notes());
        }
        if (req.status() != null) {
            entity.setStatus(req.status());
        }
    }
}

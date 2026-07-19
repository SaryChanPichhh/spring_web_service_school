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
        if (req.couponId() != null) {
            com.example.web_service.feature.admin.coupon.model.Coupon _m = new com.example.web_service.feature.admin.coupon.model.Coupon();
            _m.setCouponId((long) req.couponId());
            entity.setCoupon(_m);
        }
        if (req.restaurantId() != null) {
            com.example.web_service.feature.admin.restaurant.model.Restaurant _m = new com.example.web_service.feature.admin.restaurant.model.Restaurant();
            _m.setResId(req.restaurantId());
            entity.setRestaurant(_m);
        }
        if (req.menuItemId() != null) {
            com.example.web_service.feature.admin.menu.model.Menu _m = new com.example.web_service.feature.admin.menu.model.Menu();
            _m.setId(req.menuItemId());
            entity.setMenuItem(_m);
        }
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
        if (req.couponId() != null) {
            com.example.web_service.feature.admin.coupon.model.Coupon _m = new com.example.web_service.feature.admin.coupon.model.Coupon();
            _m.setCouponId((long) req.couponId());
            entity.setCoupon(_m);
        }
        if (req.restaurantId() != null) {
            com.example.web_service.feature.admin.restaurant.model.Restaurant _m = new com.example.web_service.feature.admin.restaurant.model.Restaurant();
            _m.setResId(req.restaurantId());
            entity.setRestaurant(_m);
        }
        if (req.menuItemId() != null) {
            com.example.web_service.feature.admin.menu.model.Menu _m = new com.example.web_service.feature.admin.menu.model.Menu();
            _m.setId(req.menuItemId());
            entity.setMenuItem(_m);
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

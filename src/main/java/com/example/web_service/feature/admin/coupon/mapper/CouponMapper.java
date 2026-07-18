package com.example.web_service.feature.admin.coupon.mapper;

import com.example.web_service.feature.admin.coupon.dto.req.*;
import com.example.web_service.feature.admin.coupon.dto.res.*;
import com.example.web_service.feature.admin.coupon.model.Coupon;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CouponMapper {

    public Coupon fromRequest(CouponRequest req) {
        if (req == null) return null;
        Coupon entity = new Coupon();
        entity.setCode(req.code());
        entity.setDescription(req.description());
        entity.setDiscountType(req.discountType());
        entity.setDiscountValue(req.discountValue());
        entity.setMinOrderAmount(req.minOrderAmount());
        entity.setMaxUsage(req.maxUsage());
        entity.setUsedCount(req.usedCount());
        entity.setStartDate(req.startDate());
        entity.setEndDate(req.endDate());
        entity.setStatus(req.status());
        return entity;
    }

    public CouponResponse toResponse(Coupon entity) {
        if (entity == null) return null;
        CouponResponse res = new CouponResponse();
        res.setCouponId(entity.getCouponId());
        res.setCode(entity.getCode());
        res.setDescription(entity.getDescription());
        res.setDiscountType(entity.getDiscountType());
        res.setDiscountValue(entity.getDiscountValue());
        res.setMinOrderAmount(entity.getMinOrderAmount());
        res.setMaxUsage(entity.getMaxUsage());
        res.setUsedCount(entity.getUsedCount());
        res.setStartDate(entity.getStartDate());
        res.setEndDate(entity.getEndDate());
        res.setStatus(entity.getStatus());
        return res;
    }

    public List<CouponResponse> toResponseList(List<Coupon> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(Coupon entity, CouponRequestUpdate req) {
        if (req == null) return;
        if (req.code() != null) {
            entity.setCode(req.code());
        }
        if (req.description() != null) {
            entity.setDescription(req.description());
        }
        if (req.discountType() != null) {
            entity.setDiscountType(req.discountType());
        }
        if (req.discountValue() != null) {
            entity.setDiscountValue(req.discountValue());
        }
        if (req.minOrderAmount() != null) {
            entity.setMinOrderAmount(req.minOrderAmount());
        }
        if (req.maxUsage() != null) {
            entity.setMaxUsage(req.maxUsage());
        }
        if (req.usedCount() != null) {
            entity.setUsedCount(req.usedCount());
        }
        if (req.startDate() != null) {
            entity.setStartDate(req.startDate());
        }
        if (req.endDate() != null) {
            entity.setEndDate(req.endDate());
        }
        if (req.status() != null) {
            entity.setStatus(req.status());
        }
    }
}

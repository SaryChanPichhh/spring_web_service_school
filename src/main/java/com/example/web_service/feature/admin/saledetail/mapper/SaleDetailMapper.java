package com.example.web_service.feature.admin.saledetail.mapper;

import com.example.web_service.feature.admin.saledetail.dto.req.*;
import com.example.web_service.feature.admin.saledetail.dto.res.*;
import com.example.web_service.feature.admin.saledetail.model.SaleDetail;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SaleDetailMapper {

    private final jakarta.persistence.EntityManager entityManager;

    public SaleDetail fromRequest(SaleDetailRequest req) {
        if (req == null) return null;
        SaleDetail entity = new SaleDetail();
        entity.setItemCode(req.itemCode());
        entity.setItemDesc(req.itemDesc());
        entity.setQty(req.qty());
        entity.setSalePrice(req.salePrice());
        entity.setTotal(req.total());
        entity.setStatus(req.status());
        if (req.saleHeaderId() != null) {
            entity.setSaleHeader(entityManager.find(com.example.web_service.feature.admin.saleheader.model.SaleHeader.class, req.saleHeaderId()));
        }
        if (req.couponId() != null) {
            entity.setCoupon(entityManager.find(com.example.web_service.feature.admin.coupon.model.Coupon.class, (long) req.couponId()));
        }
        if (req.restaurantId() != null) {
            entity.setRestaurant(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantId()));
        }
        return entity;
    }

    public SaleDetailResponse toResponse(SaleDetail entity) {
        if (entity == null) return null;
        SaleDetailResponse res = new SaleDetailResponse();
        res.setId(entity.getId());
        res.setItemCode(entity.getItemCode());
        res.setItemDesc(entity.getItemDesc());
        res.setQty(entity.getQty());
        res.setSalePrice(entity.getSalePrice());
        res.setTotal(entity.getTotal());
        res.setStatus(entity.getStatus());
        res.setSaleHeader(entity.getSaleHeader());
        res.setCoupon(entity.getCoupon());
        res.setRestaurant(entity.getRestaurant());
        return res;
    }

    public List<SaleDetailResponse> toResponseList(List<SaleDetail> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(SaleDetail entity, SaleDetailRequestUpdate req) {
        if (req == null) return;
        if (req.itemCode() != null) {
            entity.setItemCode(req.itemCode());
        }
        if (req.itemDesc() != null) {
            entity.setItemDesc(req.itemDesc());
        }
        if (req.qty() != null) {
            entity.setQty(req.qty());
        }
        if (req.salePrice() != null) {
            entity.setSalePrice(req.salePrice());
        }
        if (req.total() != null) {
            entity.setTotal(req.total());
        }
        if (req.status() != null) {
            entity.setStatus(req.status());
        }
        if (req.saleHeaderId() != null) {
            entity.setSaleHeader(entityManager.find(com.example.web_service.feature.admin.saleheader.model.SaleHeader.class, req.saleHeaderId()));
        }
        if (req.couponId() != null) {
            entity.setCoupon(entityManager.find(com.example.web_service.feature.admin.coupon.model.Coupon.class, (long) req.couponId()));
        }
        if (req.restaurantId() != null) {
            entity.setRestaurant(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantId()));
        }
    }
}

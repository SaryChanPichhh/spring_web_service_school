package com.example.web_service.feature.admin.saledetail.mapper;

import com.example.web_service.feature.admin.saledetail.dto.req.*;
import com.example.web_service.feature.admin.saledetail.dto.res.*;
import com.example.web_service.feature.admin.saledetail.model.SaleDetail;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleDetailMapper {

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
            com.example.web_service.feature.admin.saleheader.model.SaleHeader _m = new com.example.web_service.feature.admin.saleheader.model.SaleHeader();
            _m.setId(req.saleHeaderId());
            entity.setSaleHeader(_m);
        }
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
            com.example.web_service.feature.admin.saleheader.model.SaleHeader _m = new com.example.web_service.feature.admin.saleheader.model.SaleHeader();
            _m.setId(req.saleHeaderId());
            entity.setSaleHeader(_m);
        }
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
    }
}

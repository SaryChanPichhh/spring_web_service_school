package com.example.web_service.feature.admin.restaurant.mapper;

import com.example.web_service.feature.admin.restaurant.dto.req.*;
import com.example.web_service.feature.admin.restaurant.dto.res.*;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

    private final jakarta.persistence.EntityManager entityManager;

    public Restaurant fromRequest(RestaurantRequest req) {
        if (req == null) return null;
        Restaurant entity = new Restaurant();
        entity.setResName(req.resName());
        entity.setDescription(req.description());
        entity.setAddress(req.address());
        entity.setOpenTime(req.openTime());
        entity.setCloseTime(req.closeTime());
        entity.setLatLng(req.latLng());
        entity.setRating(req.rating());
        entity.setAvgEstimateTime(req.avgEstimateTime());
        entity.setImageUrl(req.imageUrl());
        entity.setIsOpen(req.isOpen());
        entity.setBasedCountry(req.basedCountry());
        entity.setCommissionRate(req.commissionRate());
        if (req.userId() != null) {
            entity.setUser(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.userId()));
        }
        return entity;
    }

    public RestaurantResponse toResponse(Restaurant entity) {
        if (entity == null) return null;
        RestaurantResponse res = new RestaurantResponse();
        res.setResId(entity.getResId());
        res.setResName(entity.getResName());
        res.setDescription(entity.getDescription());
        res.setAddress(entity.getAddress());
        res.setOpenTime(entity.getOpenTime());
        res.setCloseTime(entity.getCloseTime());
        res.setLatLng(entity.getLatLng());
        res.setRating(entity.getRating());
        res.setAvgEstimateTime(entity.getAvgEstimateTime());
        res.setImageUrl(entity.getImageUrl());
        res.setIsOpen(entity.getIsOpen());
        res.setBasedCountry(entity.getBasedCountry());
        res.setCommissionRate(entity.getCommissionRate());
        res.setUser(entity.getUser());
        return res;
    }

    public List<RestaurantResponse> toResponseList(List<Restaurant> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(Restaurant entity, RestaurantRequestUpdate req) {
        if (req == null) return;
        if (req.resName() != null) {
            entity.setResName(req.resName());
        }
        if (req.description() != null) {
            entity.setDescription(req.description());
        }
        if (req.address() != null) {
            entity.setAddress(req.address());
        }
        if (req.openTime() != null) {
            entity.setOpenTime(req.openTime());
        }
        if (req.closeTime() != null) {
            entity.setCloseTime(req.closeTime());
        }
        if (req.latLng() != null) {
            entity.setLatLng(req.latLng());
        }
        if (req.rating() != null) {
            entity.setRating(req.rating());
        }
        if (req.avgEstimateTime() != null) {
            entity.setAvgEstimateTime(req.avgEstimateTime());
        }
        if (req.imageUrl() != null) {
            entity.setImageUrl(req.imageUrl());
        }
        if (req.isOpen() != null) {
            entity.setIsOpen(req.isOpen());
        }
        if (req.basedCountry() != null) {
            entity.setBasedCountry(req.basedCountry());
        }
        if (req.commissionRate() != null) {
            entity.setCommissionRate(req.commissionRate());
        }
        if (req.userId() != null) {
            entity.setUser(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.userId()));
        }
    }
}

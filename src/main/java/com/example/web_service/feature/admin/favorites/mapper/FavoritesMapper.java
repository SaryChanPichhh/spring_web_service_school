package com.example.web_service.feature.admin.favorites.mapper;

import com.example.web_service.feature.admin.favorites.dto.req.*;
import com.example.web_service.feature.admin.favorites.dto.res.*;
import com.example.web_service.feature.admin.favorites.model.Favorites;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavoritesMapper {

    public Favorites fromRequest(FavoritesRequest req) {
        if (req == null) return null;
        Favorites entity = new Favorites();
        entity.setStatus(req.status());
        entity.setUser(req.user());
        entity.setRestaurants(req.restaurants());
        entity.setMenu(req.menu());
        return entity;
    }

    public FavoritesResponse toResponse(Favorites entity) {
        if (entity == null) return null;
        FavoritesResponse res = new FavoritesResponse();
        res.setId(entity.getFavId());
        res.setStatus(entity.getStatus());
        res.setUser(entity.getUser());
        res.setRestaurants(entity.getRestaurants());
        res.setMenu(entity.getMenu());
        return res;
    }

    public List<FavoritesResponse> toResponseList(List<Favorites> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(Favorites entity, FavoritesRequestUpdate req) {
        if (req == null) return;
        if (req.status() != null) {
            entity.setStatus(req.status());
        }
        if (req.user() != null) {
            entity.setUser(req.user());
        }
        if (req.restaurants() != null) {
            entity.setRestaurants(req.restaurants());
        }
        if (req.menu() != null) {
            entity.setMenu(req.menu());
        }
    }
}

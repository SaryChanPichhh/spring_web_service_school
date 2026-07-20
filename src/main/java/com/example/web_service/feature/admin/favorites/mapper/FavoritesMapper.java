package com.example.web_service.feature.admin.favorites.mapper;

import com.example.web_service.feature.admin.favorites.dto.req.*;
import com.example.web_service.feature.admin.favorites.dto.res.*;
import com.example.web_service.feature.admin.favorites.model.Favorites;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FavoritesMapper {

    private final jakarta.persistence.EntityManager entityManager;

    public Favorites fromRequest(FavoritesRequest req) {
        if (req == null) return null;
        Favorites entity = new Favorites();
        entity.setStatus(req.status());
        if (req.userId() != null) {
            entity.setUser(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.userId()));
        }
        if (req.restaurantsId() != null) {
            entity.setRestaurants(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantsId()));
        }
        if (req.menuId() != null) {
            entity.setMenu(entityManager.find(com.example.web_service.feature.admin.menu.model.Menu.class, req.menuId()));
        }
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
        if (req.userId() != null) {
            entity.setUser(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.userId()));
        }
        if (req.restaurantsId() != null) {
            entity.setRestaurants(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantsId()));
        }
        if (req.menuId() != null) {
            entity.setMenu(entityManager.find(com.example.web_service.feature.admin.menu.model.Menu.class, req.menuId()));
        }
    }
}

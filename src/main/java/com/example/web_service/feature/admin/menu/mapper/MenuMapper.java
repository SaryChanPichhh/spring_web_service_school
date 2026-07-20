package com.example.web_service.feature.admin.menu.mapper;

import com.example.web_service.feature.admin.menu.dto.req.*;
import com.example.web_service.feature.admin.menu.dto.res.*;
import com.example.web_service.feature.admin.menu.model.Menu;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MenuMapper {

    private final jakarta.persistence.EntityManager entityManager;

    public Menu fromRequest(MenuRequest req) {
        if (req == null) return null;
        Menu entity = new Menu();
        entity.setName(req.name());
        entity.setPrice(req.price());
        entity.setDescription(req.description());
        entity.setImage(req.image());
        entity.setRating(req.rating());
        if (req.restaurantsId() != null) {
            entity.setRestaurants(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantsId()));
        }
        return entity;
    }

    public MenuResponse toResponse(Menu entity) {
        if (entity == null) return null;
        MenuResponse res = new MenuResponse();
        res.setId(entity.getId());
        res.setName(entity.getName());
        res.setPrice(entity.getPrice());
        res.setDescription(entity.getDescription());
        res.setImage(entity.getImage());
        res.setRating(entity.getRating());
        res.setRestaurants(entity.getRestaurants());
        return res;
    }

    public List<MenuResponse> toResponseList(List<Menu> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(Menu entity, MenuRequestUpdate req) {
        if (req == null) return;
        if (req.name() != null) {
            entity.setName(req.name());
        }
        if (req.price() != null) {
            entity.setPrice(req.price());
        }
        if (req.description() != null) {
            entity.setDescription(req.description());
        }
        if (req.image() != null) {
            entity.setImage(req.image());
        }
        if (req.rating() != null) {
            entity.setRating(req.rating());
        }
        if (req.restaurantsId() != null) {
            entity.setRestaurants(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantsId()));
        }
    }
}

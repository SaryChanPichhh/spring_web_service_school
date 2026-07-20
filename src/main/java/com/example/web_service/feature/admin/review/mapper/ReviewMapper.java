package com.example.web_service.feature.admin.review.mapper;

import com.example.web_service.feature.admin.review.dto.req.*;
import com.example.web_service.feature.admin.review.dto.res.*;
import com.example.web_service.feature.admin.review.model.Review;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    private final jakarta.persistence.EntityManager entityManager;

    public Review fromRequest(ReviewRequest req) {
        if (req == null) return null;
        Review entity = new Review();
        if (req.userId() != null) {
            entity.setUser(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.userId()));
        }
        if (req.restaurantId() != null) {
            entity.setRestaurant(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantId()));
        }
        entity.setRating(req.rating());
        entity.setComment(req.comment());
        return entity;
    }

    public ReviewResponse toResponse(Review entity) {
        if (entity == null) return null;
        ReviewResponse res = new ReviewResponse();
        res.setId(entity.getId());
        res.setUser(entity.getUser());
        res.setRestaurant(entity.getRestaurant());
        res.setRating(entity.getRating());
        res.setComment(entity.getComment());
        return res;
    }

    public List<ReviewResponse> toResponseList(List<Review> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(Review entity, ReviewRequestUpdate req) {
        if (req == null) return;
        if (req.userId() != null) {
            entity.setUser(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.userId()));
        }
        if (req.restaurantId() != null) {
            entity.setRestaurant(entityManager.find(com.example.web_service.feature.admin.restaurant.model.Restaurant.class, req.restaurantId()));
        }
        if (req.rating() != null) {
            entity.setRating(req.rating());
        }
        if (req.comment() != null) {
            entity.setComment(req.comment());
        }
    }
}

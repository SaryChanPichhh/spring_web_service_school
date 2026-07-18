package com.example.web_service.feature.admin.review.repository;

import com.example.web_service.feature.admin.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}

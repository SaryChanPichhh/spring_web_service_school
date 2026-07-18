package com.example.web_service.feature.admin.favorites.repository;

import com.example.web_service.feature.admin.favorites.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {
}

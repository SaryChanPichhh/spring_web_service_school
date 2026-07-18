package com.example.web_service.feature.admin.user.repository;

import com.example.web_service.feature.admin.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

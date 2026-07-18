package com.example.web_service.feature.admin.couponassignment.repository;

import com.example.web_service.feature.admin.couponassignment.model.CouponAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponAssignmentRepository extends JpaRepository<CouponAssignment, Long> {
}

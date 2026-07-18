package com.example.web_service.feature.admin.freedeliveryassignment.repository;

import com.example.web_service.feature.admin.freedeliveryassignment.model.FreeDeliveryAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeDeliveryAssignmentRepository extends JpaRepository<FreeDeliveryAssignment, Long> {
}

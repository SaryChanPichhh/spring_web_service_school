package com.example.web_service.feature.admin.saledetail.repository;

import com.example.web_service.feature.admin.saledetail.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {
}

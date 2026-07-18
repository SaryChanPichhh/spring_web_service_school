package com.example.web_service.feature.admin.saleheader.repository;

import com.example.web_service.feature.admin.saleheader.model.SaleHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleHeaderRepository extends JpaRepository<SaleHeader, Integer> {
}

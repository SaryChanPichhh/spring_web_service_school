package com.example.web_service.feature.admin.saledetail.model;

import com.example.web_service.shared.BasedEntity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.coupon.model.Coupon;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.admin.saleheader.model.SaleHeader;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "saledetails")
public class SaleDetail extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String itemCode;
    private String itemDesc;
    private Integer qty;
    private Double salePrice;
    private Double total;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "header_id")
    @JsonIgnore
    private SaleHeader saleHeader;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;
}


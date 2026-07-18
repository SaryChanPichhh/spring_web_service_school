package com.example.web_service.feature.admin.saledetail.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.coupon.model.Coupon;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.admin.saleheader.model.SaleHeader;

@Getter
@Setter
public class SaleDetailResponse {
    private Integer id;
    private String itemCode;
    private String itemDesc;
    private Integer qty;
    private Double salePrice;
    private Double total;
    private Boolean status;
    private SaleHeader saleHeader;
    private Coupon coupon;
    private Restaurant restaurant;
}

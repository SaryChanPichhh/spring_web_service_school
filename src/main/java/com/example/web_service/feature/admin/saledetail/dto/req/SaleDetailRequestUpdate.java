package com.example.web_service.feature.admin.saledetail.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.coupon.model.Coupon;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.admin.saleheader.model.SaleHeader;

public record SaleDetailRequestUpdate(
    String itemCode,
    String itemDesc,
    Integer qty,
    Double salePrice,
    Double total,
    Boolean status,
    SaleHeader saleHeader,
    Coupon coupon,
    Restaurant restaurant
) {
}

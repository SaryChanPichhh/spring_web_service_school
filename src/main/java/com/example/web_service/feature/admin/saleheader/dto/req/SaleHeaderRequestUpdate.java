package com.example.web_service.feature.admin.saleheader.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.delivery.model.Delivery;
import com.example.web_service.feature.admin.exchangerate.model.ExchangeRate;
import com.example.web_service.feature.admin.saledetail.model.SaleDetail;
import com.example.web_service.feature.admin.user.model.User;
import java.util.List;

public record SaleHeaderRequestUpdate(
    Double total,
    String invoiceType,
    Boolean status,
    Integer userId,
    Integer deliveryId,
    String paymentMethod,
    Double commissionAmount,
    Double exchangeRate,
    Integer exchangeRateModelId
) {
}

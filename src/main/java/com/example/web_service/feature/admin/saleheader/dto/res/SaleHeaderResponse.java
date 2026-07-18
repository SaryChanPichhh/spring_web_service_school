package com.example.web_service.feature.admin.saleheader.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.delivery.model.Delivery;
import com.example.web_service.feature.admin.exchangerate.model.ExchangeRate;
import com.example.web_service.feature.admin.saledetail.model.SaleDetail;
import com.example.web_service.feature.admin.user.model.User;
import java.util.List;

@Getter
@Setter
public class SaleHeaderResponse {
    private Integer id;
    private Double total;
    private String invoiceType;
    private Boolean status;
    private User user;
    private Delivery delivery;
    private List<SaleDetail> saleDetails;
    private String paymentMethod;
    private Double commissionAmount;
    private Double exchangeRate;
    private ExchangeRate exchangeRateModel;
}

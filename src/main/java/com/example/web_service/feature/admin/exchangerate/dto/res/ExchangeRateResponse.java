package com.example.web_service.feature.admin.exchangerate.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ExchangeRateResponse {
    private Long id;
    private String currencyCode;
    private String currencyName;
    private Double rate;
    private String symbol;
    private Boolean defaultRate;
}

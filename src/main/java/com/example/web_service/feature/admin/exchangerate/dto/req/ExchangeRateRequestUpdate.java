package com.example.web_service.feature.admin.exchangerate.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ExchangeRateRequestUpdate(
    String currencyCode,
    String currencyName,
    Double rate,
    String symbol,
    Boolean defaultRate
) {
}

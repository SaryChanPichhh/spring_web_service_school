package com.example.web_service.feature.admin.exchangerate.model;

import com.example.web_service.shared.BasedEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exchangerates")
public class ExchangeRate extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currencyCode;
    private String currencyName;
    private Double rate;
    private String symbol;
    private Boolean defaultRate;
}

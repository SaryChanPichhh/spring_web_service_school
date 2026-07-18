package com.example.web_service.feature.admin.saleheader.model;

import com.example.web_service.shared.BasedEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.delivery.model.Delivery;
import com.example.web_service.feature.admin.exchangerate.model.ExchangeRate;
import com.example.web_service.feature.admin.saledetail.model.SaleDetail;
import com.example.web_service.feature.admin.user.model.User;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "saleheaders")
public class SaleHeader extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double total;
    private String invoiceType;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    @OneToMany(mappedBy = "saleHeader")
    private List<SaleDetail> saleDetails;
    private String paymentMethod;
    private Double commissionAmount;
    private Double exchangeRate;
    @ManyToOne
    @JoinColumn(name = "exchange_rate_id")
    private ExchangeRate exchangeRateModel;
}

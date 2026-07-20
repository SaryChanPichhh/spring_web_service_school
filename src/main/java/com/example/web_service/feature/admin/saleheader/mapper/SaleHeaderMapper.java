package com.example.web_service.feature.admin.saleheader.mapper;

import com.example.web_service.feature.admin.saleheader.dto.req.*;
import com.example.web_service.feature.admin.saleheader.dto.res.*;
import com.example.web_service.feature.admin.saleheader.model.SaleHeader;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SaleHeaderMapper {

    private final jakarta.persistence.EntityManager entityManager;

    public SaleHeader fromRequest(SaleHeaderRequest req) {
        if (req == null) return null;
        SaleHeader entity = new SaleHeader();
        entity.setTotal(req.total());
        entity.setInvoiceType(req.invoiceType());
        entity.setStatus(req.status());
        if (req.userId() != null) {
            entity.setUser(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.userId()));
        }
        if (req.deliveryId() != null) {
            entity.setDelivery(entityManager.find(com.example.web_service.feature.admin.delivery.model.Delivery.class, (long) req.deliveryId()));
        }
        entity.setPaymentMethod(req.paymentMethod());
        entity.setCommissionAmount(req.commissionAmount());
        entity.setExchangeRate(req.exchangeRate());
        if (req.exchangeRateModelId() != null) {
            entity.setExchangeRateModel(entityManager.find(com.example.web_service.feature.admin.exchangerate.model.ExchangeRate.class, (long) req.exchangeRateModelId()));
        }
        return entity;
    }

    public SaleHeaderResponse toResponse(SaleHeader entity) {
        if (entity == null) return null;
        SaleHeaderResponse res = new SaleHeaderResponse();
        res.setId(entity.getId());
        res.setTotal(entity.getTotal());
        res.setInvoiceType(entity.getInvoiceType());
        res.setStatus(entity.getStatus());
        res.setUser(entity.getUser());
        res.setDelivery(entity.getDelivery());
        res.setPaymentMethod(entity.getPaymentMethod());
        res.setCommissionAmount(entity.getCommissionAmount());
        res.setExchangeRate(entity.getExchangeRate());
        res.setExchangeRateModel(entity.getExchangeRateModel());
        return res;
    }

    public List<SaleHeaderResponse> toResponseList(List<SaleHeader> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(SaleHeader entity, SaleHeaderRequestUpdate req) {
        if (req == null) return;
        if (req.total() != null) {
            entity.setTotal(req.total());
        }
        if (req.invoiceType() != null) {
            entity.setInvoiceType(req.invoiceType());
        }
        if (req.status() != null) {
            entity.setStatus(req.status());
        }
        if (req.userId() != null) {
            entity.setUser(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.userId()));
        }
        if (req.deliveryId() != null) {
            entity.setDelivery(entityManager.find(com.example.web_service.feature.admin.delivery.model.Delivery.class, (long) req.deliveryId()));
        }
        if (req.paymentMethod() != null) {
            entity.setPaymentMethod(req.paymentMethod());
        }
        if (req.commissionAmount() != null) {
            entity.setCommissionAmount(req.commissionAmount());
        }
        if (req.exchangeRate() != null) {
            entity.setExchangeRate(req.exchangeRate());
        }
        if (req.exchangeRateModelId() != null) {
            entity.setExchangeRateModel(entityManager.find(com.example.web_service.feature.admin.exchangerate.model.ExchangeRate.class, (long) req.exchangeRateModelId()));
        }
    }
}

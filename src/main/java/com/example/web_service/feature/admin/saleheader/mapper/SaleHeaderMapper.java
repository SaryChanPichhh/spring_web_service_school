package com.example.web_service.feature.admin.saleheader.mapper;

import com.example.web_service.feature.admin.saleheader.dto.req.*;
import com.example.web_service.feature.admin.saleheader.dto.res.*;
import com.example.web_service.feature.admin.saleheader.model.SaleHeader;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleHeaderMapper {

    public SaleHeader fromRequest(SaleHeaderRequest req) {
        if (req == null) return null;
        SaleHeader entity = new SaleHeader();
        entity.setTotal(req.total());
        entity.setInvoiceType(req.invoiceType());
        entity.setStatus(req.status());
        if (req.userId() != null) {
            com.example.web_service.feature.admin.user.model.User _m = new com.example.web_service.feature.admin.user.model.User();
            _m.setId(req.userId());
            entity.setUser(_m);
        }
        if (req.deliveryId() != null) {
            com.example.web_service.feature.admin.delivery.model.Delivery _m = new com.example.web_service.feature.admin.delivery.model.Delivery();
            _m.setId((long) req.deliveryId());
            entity.setDelivery(_m);
        }
        entity.setSaleDetails(req.saleDetails());
        entity.setPaymentMethod(req.paymentMethod());
        entity.setCommissionAmount(req.commissionAmount());
        entity.setExchangeRate(req.exchangeRate());
        if (req.exchangeRateModelId() != null) {
            com.example.web_service.feature.admin.exchangerate.model.ExchangeRate _m = new com.example.web_service.feature.admin.exchangerate.model.ExchangeRate();
            _m.setId((long) req.exchangeRateModelId());
            entity.setExchangeRateModel(_m);
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
        res.setSaleDetails(entity.getSaleDetails());
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
            com.example.web_service.feature.admin.user.model.User _m = new com.example.web_service.feature.admin.user.model.User();
            _m.setId(req.userId());
            entity.setUser(_m);
        }
        if (req.deliveryId() != null) {
            com.example.web_service.feature.admin.delivery.model.Delivery _m = new com.example.web_service.feature.admin.delivery.model.Delivery();
            _m.setId((long) req.deliveryId());
            entity.setDelivery(_m);
        }
        if (req.saleDetails() != null) {
            entity.setSaleDetails(req.saleDetails());
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
            com.example.web_service.feature.admin.exchangerate.model.ExchangeRate _m = new com.example.web_service.feature.admin.exchangerate.model.ExchangeRate();
            _m.setId((long) req.exchangeRateModelId());
            entity.setExchangeRateModel(_m);
        }
    }
}

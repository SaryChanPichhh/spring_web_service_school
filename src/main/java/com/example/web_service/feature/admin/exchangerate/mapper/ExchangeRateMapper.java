package com.example.web_service.feature.admin.exchangerate.mapper;

import com.example.web_service.feature.admin.exchangerate.dto.req.*;
import com.example.web_service.feature.admin.exchangerate.dto.res.*;
import com.example.web_service.feature.admin.exchangerate.model.ExchangeRate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExchangeRateMapper {

    public ExchangeRate fromRequest(ExchangeRateRequest req) {
        if (req == null) return null;
        ExchangeRate entity = new ExchangeRate();
        entity.setCurrencyCode(req.currencyCode());
        entity.setCurrencyName(req.currencyName());
        entity.setRate(req.rate());
        entity.setSymbol(req.symbol());
        entity.setDefaultRate(req.defaultRate());
        return entity;
    }

    public ExchangeRateResponse toResponse(ExchangeRate entity) {
        if (entity == null) return null;
        ExchangeRateResponse res = new ExchangeRateResponse();
        res.setId(entity.getId());
        res.setCurrencyCode(entity.getCurrencyCode());
        res.setCurrencyName(entity.getCurrencyName());
        res.setRate(entity.getRate());
        res.setSymbol(entity.getSymbol());
        res.setDefaultRate(entity.getDefaultRate());
        return res;
    }

    public List<ExchangeRateResponse> toResponseList(List<ExchangeRate> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(ExchangeRate entity, ExchangeRateRequestUpdate req) {
        if (req == null) return;
        if (req.currencyCode() != null) {
            entity.setCurrencyCode(req.currencyCode());
        }
        if (req.currencyName() != null) {
            entity.setCurrencyName(req.currencyName());
        }
        if (req.rate() != null) {
            entity.setRate(req.rate());
        }
        if (req.symbol() != null) {
            entity.setSymbol(req.symbol());
        }
        if (req.defaultRate() != null) {
            entity.setDefaultRate(req.defaultRate());
        }
    }
}

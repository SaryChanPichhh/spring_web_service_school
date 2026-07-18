package com.example.web_service.feature.admin.exchangerate.serviceImpl;

import com.example.web_service.feature.admin.exchangerate.dto.req.*;
import com.example.web_service.feature.admin.exchangerate.dto.res.*;
import com.example.web_service.feature.admin.exchangerate.mapper.ExchangeRateMapper;
import com.example.web_service.feature.admin.exchangerate.model.ExchangeRate;
import com.example.web_service.feature.admin.exchangerate.repository.ExchangeRateRepository;
import com.example.web_service.feature.admin.exchangerate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImplementation implements ExchangeRateService {
    private final ExchangeRateRepository repository;
    private final ExchangeRateMapper mapper;

    @Override
    public List<ExchangeRateResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public ExchangeRateResponse getById(Long id) {
        ExchangeRate entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public ExchangeRateResponse create(ExchangeRateRequest req) {
        ExchangeRate entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public ExchangeRateResponse update(Long id, ExchangeRateRequestUpdate reqUpdate) {
        ExchangeRate entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public ExchangeRateResponse delete(Long id) {
        ExchangeRate entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public ExchangeRatePageResponseDto<ExchangeRateResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<ExchangeRate> pageResult = repository.findAll(pageable);
        ExchangeRatePageResponseDto<ExchangeRateResponse> response = new ExchangeRatePageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

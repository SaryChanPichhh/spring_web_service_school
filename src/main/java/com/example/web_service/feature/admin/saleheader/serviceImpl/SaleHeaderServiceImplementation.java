package com.example.web_service.feature.admin.saleheader.serviceImpl;

import com.example.web_service.feature.admin.saleheader.dto.req.*;
import com.example.web_service.feature.admin.saleheader.dto.res.*;
import com.example.web_service.feature.admin.saleheader.mapper.SaleHeaderMapper;
import com.example.web_service.feature.admin.saleheader.model.SaleHeader;
import com.example.web_service.feature.admin.saleheader.repository.SaleHeaderRepository;
import com.example.web_service.feature.admin.saleheader.service.SaleHeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleHeaderServiceImplementation implements SaleHeaderService {
    private final SaleHeaderRepository repository;
    private final SaleHeaderMapper mapper;

    @Override
    public List<SaleHeaderResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public SaleHeaderResponse getById(Long id) {
        Integer searchId = id.intValue();
        SaleHeader entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public SaleHeaderResponse create(SaleHeaderRequest req) {
        SaleHeader entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public SaleHeaderResponse update(Long id, SaleHeaderRequestUpdate reqUpdate) {
        Integer searchId = id.intValue();
        SaleHeader entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public SaleHeaderResponse delete(Long id) {
        Integer searchId = id.intValue();
        SaleHeader entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public SaleHeaderPageResponseDto<SaleHeaderResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<SaleHeader> pageResult = repository.findAll(pageable);
        SaleHeaderPageResponseDto<SaleHeaderResponse> response = new SaleHeaderPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

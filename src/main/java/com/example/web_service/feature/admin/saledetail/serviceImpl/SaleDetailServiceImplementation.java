package com.example.web_service.feature.admin.saledetail.serviceImpl;

import com.example.web_service.feature.admin.saledetail.dto.req.*;
import com.example.web_service.feature.admin.saledetail.dto.res.*;
import com.example.web_service.feature.admin.saledetail.mapper.SaleDetailMapper;
import com.example.web_service.feature.admin.saledetail.model.SaleDetail;
import com.example.web_service.feature.admin.saledetail.repository.SaleDetailRepository;
import com.example.web_service.feature.admin.saledetail.service.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleDetailServiceImplementation implements SaleDetailService {
    private final SaleDetailRepository repository;
    private final SaleDetailMapper mapper;

    @Override
    public List<SaleDetailResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public SaleDetailResponse getById(Long id) {
        Integer searchId = (Integer) (Object) id;
        SaleDetail entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public SaleDetailResponse create(SaleDetailRequest req) {
        SaleDetail entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public SaleDetailResponse update(Long id, SaleDetailRequestUpdate reqUpdate) {
        Integer searchId = (Integer) (Object) id;
        SaleDetail entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public SaleDetailResponse delete(Long id) {
        Integer searchId = (Integer) (Object) id;
        SaleDetail entity = repository.findById(searchId).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public SaleDetailPageResponseDto<SaleDetailResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<SaleDetail> pageResult = repository.findAll(pageable);
        SaleDetailPageResponseDto<SaleDetailResponse> response = new SaleDetailPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

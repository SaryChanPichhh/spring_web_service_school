package com.example.web_service.feature.admin.coupon.serviceImpl;

import com.example.web_service.feature.admin.coupon.dto.req.*;
import com.example.web_service.feature.admin.coupon.dto.res.*;
import com.example.web_service.feature.admin.coupon.mapper.CouponMapper;
import com.example.web_service.feature.admin.coupon.model.Coupon;
import com.example.web_service.feature.admin.coupon.repository.CouponRepository;
import com.example.web_service.feature.admin.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImplementation implements CouponService {
    private final CouponRepository repository;
    private final CouponMapper mapper;

    @Override
    public List<CouponResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public CouponResponse getById(Long id) {
        Coupon entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public CouponResponse create(CouponRequest req) {
        Coupon entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public CouponResponse update(Long id, CouponRequestUpdate reqUpdate) {
        Coupon entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public CouponResponse delete(Long id) {
        Coupon entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public CouponPageResponseDto<CouponResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<Coupon> pageResult = repository.findAll(pageable);
        CouponPageResponseDto<CouponResponse> response = new CouponPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

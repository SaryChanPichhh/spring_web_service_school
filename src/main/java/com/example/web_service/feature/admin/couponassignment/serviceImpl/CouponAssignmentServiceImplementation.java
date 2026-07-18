package com.example.web_service.feature.admin.couponassignment.serviceImpl;

import com.example.web_service.feature.admin.couponassignment.dto.req.*;
import com.example.web_service.feature.admin.couponassignment.dto.res.*;
import com.example.web_service.feature.admin.couponassignment.mapper.CouponAssignmentMapper;
import com.example.web_service.feature.admin.couponassignment.model.CouponAssignment;
import com.example.web_service.feature.admin.couponassignment.repository.CouponAssignmentRepository;
import com.example.web_service.feature.admin.couponassignment.service.CouponAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponAssignmentServiceImplementation implements CouponAssignmentService {
    private final CouponAssignmentRepository repository;
    private final CouponAssignmentMapper mapper;

    @Override
    public List<CouponAssignmentResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public CouponAssignmentResponse getById(Long id) {
        CouponAssignment entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public CouponAssignmentResponse create(CouponAssignmentRequest req) {
        CouponAssignment entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public CouponAssignmentResponse update(Long id, CouponAssignmentRequestUpdate reqUpdate) {
        CouponAssignment entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public CouponAssignmentResponse delete(Long id) {
        CouponAssignment entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public CouponAssignmentPageResponseDto<CouponAssignmentResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<CouponAssignment> pageResult = repository.findAll(pageable);
        CouponAssignmentPageResponseDto<CouponAssignmentResponse> response = new CouponAssignmentPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

package com.example.web_service.feature.admin.freedeliveryassignment.serviceImpl;

import com.example.web_service.feature.admin.freedeliveryassignment.dto.req.*;
import com.example.web_service.feature.admin.freedeliveryassignment.dto.res.*;
import com.example.web_service.feature.admin.freedeliveryassignment.mapper.FreeDeliveryAssignmentMapper;
import com.example.web_service.feature.admin.freedeliveryassignment.model.FreeDeliveryAssignment;
import com.example.web_service.feature.admin.freedeliveryassignment.repository.FreeDeliveryAssignmentRepository;
import com.example.web_service.feature.admin.freedeliveryassignment.service.FreeDeliveryAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeDeliveryAssignmentServiceImplementation implements FreeDeliveryAssignmentService {
    private final FreeDeliveryAssignmentRepository repository;
    private final FreeDeliveryAssignmentMapper mapper;

    @Override
    public List<FreeDeliveryAssignmentResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public FreeDeliveryAssignmentResponse getById(Long id) {
        FreeDeliveryAssignment entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public FreeDeliveryAssignmentResponse create(FreeDeliveryAssignmentRequest req) {
        FreeDeliveryAssignment entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public FreeDeliveryAssignmentResponse update(Long id, FreeDeliveryAssignmentRequestUpdate reqUpdate) {
        FreeDeliveryAssignment entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public FreeDeliveryAssignmentResponse delete(Long id) {
        FreeDeliveryAssignment entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public FreeDeliveryAssignmentPageResponseDto<FreeDeliveryAssignmentResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<FreeDeliveryAssignment> pageResult = repository.findAll(pageable);
        FreeDeliveryAssignmentPageResponseDto<FreeDeliveryAssignmentResponse> response = new FreeDeliveryAssignmentPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

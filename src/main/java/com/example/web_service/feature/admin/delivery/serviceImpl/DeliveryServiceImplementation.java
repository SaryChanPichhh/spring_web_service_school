package com.example.web_service.feature.admin.delivery.serviceImpl;

import com.example.web_service.feature.admin.delivery.dto.req.*;
import com.example.web_service.feature.admin.delivery.dto.res.*;
import com.example.web_service.feature.admin.delivery.mapper.DeliveryMapper;
import com.example.web_service.feature.admin.delivery.model.Delivery;
import com.example.web_service.feature.admin.delivery.repository.DeliveryRepository;
import com.example.web_service.feature.admin.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImplementation implements DeliveryService {
    private final DeliveryRepository repository;
    private final DeliveryMapper mapper;

    @Override
    public List<DeliveryResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public DeliveryResponse getById(Long id) {
        Delivery entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public DeliveryResponse create(DeliveryRequest req) {
        Delivery entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public DeliveryResponse update(Long id, DeliveryRequestUpdate reqUpdate) {
        Delivery entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public DeliveryResponse delete(Long id) {
        Delivery entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public DeliveryPageResponseDto<DeliveryResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<Delivery> pageResult = repository.findAll(pageable);
        DeliveryPageResponseDto<DeliveryResponse> response = new DeliveryPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

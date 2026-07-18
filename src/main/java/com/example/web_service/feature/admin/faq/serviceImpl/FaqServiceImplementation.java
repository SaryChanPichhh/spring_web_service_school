package com.example.web_service.feature.admin.faq.serviceImpl;

import com.example.web_service.feature.admin.faq.dto.req.*;
import com.example.web_service.feature.admin.faq.dto.res.*;
import com.example.web_service.feature.admin.faq.mapper.FaqMapper;
import com.example.web_service.feature.admin.faq.model.Faq;
import com.example.web_service.feature.admin.faq.repository.FaqRepository;
import com.example.web_service.feature.admin.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqServiceImplementation implements FaqService {
    private final FaqRepository repository;
    private final FaqMapper mapper;

    @Override
    public List<FaqResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public FaqResponse getById(Long id) {
        Faq entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public FaqResponse create(FaqRequest req) {
        Faq entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public FaqResponse update(Long id, FaqRequestUpdate reqUpdate) {
        Faq entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public FaqResponse delete(Long id) {
        Faq entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public FaqPageResponseDto<FaqResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<Faq> pageResult = repository.findAll(pageable);
        FaqPageResponseDto<FaqResponse> response = new FaqPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

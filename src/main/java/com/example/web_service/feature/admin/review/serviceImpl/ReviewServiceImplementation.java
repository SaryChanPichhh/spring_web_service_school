package com.example.web_service.feature.admin.review.serviceImpl;

import com.example.web_service.feature.admin.review.dto.req.*;
import com.example.web_service.feature.admin.review.dto.res.*;
import com.example.web_service.feature.admin.review.mapper.ReviewMapper;
import com.example.web_service.feature.admin.review.model.Review;
import com.example.web_service.feature.admin.review.repository.ReviewRepository;
import com.example.web_service.feature.admin.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    @Override
    public List<ReviewResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public ReviewResponse getById(Long id) {
        Review entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public ReviewResponse create(ReviewRequest req) {
        Review entity = mapper.fromRequest(req);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public ReviewResponse update(Long id, ReviewRequestUpdate reqUpdate) {
        Review entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public ReviewResponse delete(Long id) {
        Review entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public ReviewPageResponseDto<ReviewResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<Review> pageResult = repository.findAll(pageable);
        ReviewPageResponseDto<ReviewResponse> response = new ReviewPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

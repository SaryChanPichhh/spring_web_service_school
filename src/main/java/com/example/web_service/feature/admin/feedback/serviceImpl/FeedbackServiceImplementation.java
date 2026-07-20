package com.example.web_service.feature.admin.feedback.serviceImpl;

import com.example.web_service.feature.admin.feedback.dto.req.*;
import com.example.web_service.feature.admin.feedback.dto.res.*;
import com.example.web_service.feature.admin.feedback.mapper.FeedbackMapper;
import com.example.web_service.feature.admin.feedback.model.Feedback;
import com.example.web_service.feature.admin.feedback.repository.FeedbackRepository;
import com.example.web_service.feature.admin.feedback.service.FeedbackService;
import com.example.web_service.feature.admin.user.repository.UserRepository;
import com.example.web_service.feature.admin.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImplementation implements FeedbackService {
    private final FeedbackRepository repository;
    private final FeedbackMapper mapper;
    private final UserRepository userRepository;

    @Override
    public List<FeedbackResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public FeedbackResponse getById(Long id) {
        Feedback entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public FeedbackResponse create(FeedbackRequest req) {
        Feedback entity = mapper.fromRequest(req);
        if (req.userId() != null) {
            User user = userRepository.findById(req.userId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            entity.setUser(user);
        }
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public FeedbackResponse update(Long id, FeedbackRequestUpdate reqUpdate) {
        Feedback entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        mapper.updateFromRequest(entity, reqUpdate);
        if (reqUpdate.userId() != null) {
            User user = userRepository.findById(reqUpdate.userId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            entity.setUser(user);
        }
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public FeedbackResponse delete(Long id) {
        Feedback entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public FeedbackPageResponseDto<FeedbackResponse> getPaginated(org.springframework.data.domain.Pageable pageable) {
        Page<Feedback> pageResult = repository.findAll(pageable);
        FeedbackPageResponseDto<FeedbackResponse> response = new FeedbackPageResponseDto<>();
        response.setContent(mapper.toResponseList(pageResult.getContent()));
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}

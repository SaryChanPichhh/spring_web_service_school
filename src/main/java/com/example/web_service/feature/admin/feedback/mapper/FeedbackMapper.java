package com.example.web_service.feature.admin.feedback.mapper;

import com.example.web_service.feature.admin.feedback.dto.req.*;
import com.example.web_service.feature.admin.feedback.dto.res.*;
import com.example.web_service.feature.admin.feedback.model.Feedback;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper {

    public Feedback fromRequest(FeedbackRequest req) {
        if (req == null) return null;
        Feedback entity = new Feedback();
        entity.setUser(req.user());
        entity.setSubject(req.subject());
        entity.setMessage(req.message());
        entity.setIsRead(req.isRead());
        return entity;
    }

    public FeedbackResponse toResponse(Feedback entity) {
        if (entity == null) return null;
        FeedbackResponse res = new FeedbackResponse();
        res.setId(entity.getId());
        res.setUser(entity.getUser());
        res.setSubject(entity.getSubject());
        res.setMessage(entity.getMessage());
        res.setIsRead(entity.getIsRead());
        return res;
    }

    public List<FeedbackResponse> toResponseList(List<Feedback> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(Feedback entity, FeedbackRequestUpdate req) {
        if (req == null) return;
        if (req.user() != null) {
            entity.setUser(req.user());
        }
        if (req.subject() != null) {
            entity.setSubject(req.subject());
        }
        if (req.message() != null) {
            entity.setMessage(req.message());
        }
        if (req.isRead() != null) {
            entity.setIsRead(req.isRead());
        }
    }
}

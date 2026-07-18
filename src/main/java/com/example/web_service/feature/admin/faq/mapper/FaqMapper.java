package com.example.web_service.feature.admin.faq.mapper;

import com.example.web_service.feature.admin.faq.dto.req.*;
import com.example.web_service.feature.admin.faq.dto.res.*;
import com.example.web_service.feature.admin.faq.model.Faq;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FaqMapper {

    public Faq fromRequest(FaqRequest req) {
        if (req == null) return null;
        Faq entity = new Faq();
        entity.setQuestion(req.question());
        entity.setAnswer(req.answer());
        entity.setSortOrder(req.sortOrder());
        entity.setActive(req.active());
        return entity;
    }

    public FaqResponse toResponse(Faq entity) {
        if (entity == null) return null;
        FaqResponse res = new FaqResponse();
        res.setId(entity.getId());
        res.setQuestion(entity.getQuestion());
        res.setAnswer(entity.getAnswer());
        res.setSortOrder(entity.getSortOrder());
        res.setActive(entity.getActive());
        return res;
    }

    public List<FaqResponse> toResponseList(List<Faq> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(Faq entity, FaqRequestUpdate req) {
        if (req == null) return;
        if (req.question() != null) {
            entity.setQuestion(req.question());
        }
        if (req.answer() != null) {
            entity.setAnswer(req.answer());
        }
        if (req.sortOrder() != null) {
            entity.setSortOrder(req.sortOrder());
        }
        if (req.active() != null) {
            entity.setActive(req.active());
        }
    }
}

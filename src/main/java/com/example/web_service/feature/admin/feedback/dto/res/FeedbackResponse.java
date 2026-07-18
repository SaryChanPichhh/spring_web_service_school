package com.example.web_service.feature.admin.feedback.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;

@Getter
@Setter
public class FeedbackResponse {
    private Long id;
    private User user;
    private String subject;
    private String message;
    private Boolean isRead;
}

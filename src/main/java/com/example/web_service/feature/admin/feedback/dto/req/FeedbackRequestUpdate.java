package com.example.web_service.feature.admin.feedback.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;

public record FeedbackRequestUpdate(
    User user,
    String subject,
    String message,
    Boolean isRead
) {
}

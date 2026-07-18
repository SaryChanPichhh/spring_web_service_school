package com.example.web_service.feature.admin.faq.dto.req;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record FaqRequest(
    String question,
    String answer,
    Integer sortOrder,
    Boolean active
) {
}

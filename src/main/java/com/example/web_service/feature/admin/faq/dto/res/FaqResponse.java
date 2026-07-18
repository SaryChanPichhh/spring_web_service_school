package com.example.web_service.feature.admin.faq.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class FaqResponse {
    private Long id;
    private String question;
    private String answer;
    private Integer sortOrder;
    private Boolean active;
}

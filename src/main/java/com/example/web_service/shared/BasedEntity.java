package com.example.web_service.shared;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BasedEntity {
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate updatedAt;
    private String updatedBy;
    private Boolean isDeleted;
    private Boolean isUpdated;
}

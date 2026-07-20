package com.example.web_service.shared;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BasedEntity {
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate updatedAt;
    private String updatedBy;
    private Boolean isDeleted;
    private Boolean isUpdated;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.isDeleted = false;
        this.isUpdated = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
        this.isUpdated = true;
    }
}

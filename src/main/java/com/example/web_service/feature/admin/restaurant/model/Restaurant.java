package com.example.web_service.feature.admin.restaurant.model;

import com.example.web_service.shared.BasedEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.user.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "restaurants")
public class Restaurant extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resId;
    private String resName;
    private String description;
    private String address;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String latLng;
    private Double rating;
    private String avgEstimateTime;
    private String imageUrl;
    private Boolean isOpen;
    private String basedCountry;
    private Double commissionRate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

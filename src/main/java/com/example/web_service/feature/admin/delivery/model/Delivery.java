package com.example.web_service.feature.admin.delivery.model;

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
@Table(name = "deliverys")
public class Delivery extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean status;
    private String imageUrl;
    private String nationalId;
    private String driverLicense;
    private String nationalIdUrl;
    private String driverLicenseUrl;
    private Integer rating;
    private String city;
    private String state;
    private String zip;
    private String country;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
}

package com.example.web_service.feature.admin.couponassignment.model;

import com.example.web_service.shared.BasedEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.web_service.feature.admin.coupon.model.Coupon;
import com.example.web_service.feature.admin.menu.model.Menu;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "couponassignments")
public class CouponAssignment extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menuItem;
    private String assignmentType;
    private String notes;
    private String status;
}

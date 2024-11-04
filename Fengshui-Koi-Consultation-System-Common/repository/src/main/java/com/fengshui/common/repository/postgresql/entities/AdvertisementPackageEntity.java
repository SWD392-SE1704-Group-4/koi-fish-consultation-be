package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "advertisementPackage")
public class AdvertisementPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "package_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "duration_in_days")
    private Integer durationInDays;   // Duration of the advertisement package in days

    @Column(name = "max_ads")
    private Integer maxAds;   // Maximum number of ads allowed in the package

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Optional: List of users for bidirectional association
    @OneToMany(mappedBy = "currentPackage")
    private List<AppUserEntity> users;

    // Optional: Bidirectional relationship with PaymentEntity
    @OneToMany(mappedBy = "advertisementPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentEntity> payments;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

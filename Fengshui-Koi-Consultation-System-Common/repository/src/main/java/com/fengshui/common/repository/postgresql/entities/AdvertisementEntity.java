package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "advertisement")
public class AdvertisementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "advertisement_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "posted_at", nullable = false)
    private LocalDateTime postedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUserEntity postedBy;  // Assuming there's a UserEntity for the user posting the ad

    @PrePersist
    protected void onCreate() {
        this.postedAt = LocalDateTime.now();
        this.isActive = true; // Default to active when posted
    }
}

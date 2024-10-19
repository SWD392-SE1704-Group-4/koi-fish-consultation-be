package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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

    @Column(name = "advertisement_title")
    private String title;

    @Column(name = "advertisement_description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "advertisement_type")
    private String advertisementType;

    @ManyToOne
    @JoinColumn(name = "koi_fish_id", referencedColumnName = "koi_fish_id")
    private KoiFishEntity koiFish;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "views_count")
    private int viewsCount;

    @Column(name = "status")
    private String status;

    @Column(name = "admin_verified")
    private boolean adminVerified;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "posted_by")
    private String postedBy;

    @ElementCollection
    @CollectionTable(name = "advertisement_images", joinColumns = @JoinColumn(name = "advertisement_id"))
    @Column(name = "additional_images")
    private List<String> additionalImages;

    @ElementCollection
    @CollectionTable(name = "advertisement_tags", joinColumns = @JoinColumn(name = "advertisement_id"))
    @Column(name = "tag")
    private List<String> tags;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.viewsCount = 0;
        this.deleted = false;
        this.adminVerified = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

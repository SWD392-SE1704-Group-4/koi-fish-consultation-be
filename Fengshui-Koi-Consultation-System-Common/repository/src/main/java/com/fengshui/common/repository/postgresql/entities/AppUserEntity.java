package com.fengshui.common.repository.postgresql.entities;

import com.fengshui.common.repository.postgresql.enums.AppUserRole;
import com.fengshui.common.repository.postgresql.enums.AppUserStatus;
import com.fengshui.common.repository.postgresql.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "app_user_id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_number_verified")
    private Boolean phoneNumberVerified;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Enumerated(EnumType.ORDINAL)
    private GenderEnum gender;

    @Column(name = "address")
    private String address;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Enumerated(EnumType.ORDINAL)
    private AppUserStatus status;

    @Enumerated(EnumType.ORDINAL)
    private AppUserRole role = AppUserRole.USER;

    @Column(name = "last_login")
    private LocalDate lastLogin;

    // One-to-Many relationship with AdvertisementEntity
    @OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdvertisementEntity> advertisements = new ArrayList<>();

    // One-to-Many relationship with FishPondEntity
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FishPondEntity> fishPonds = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "current_package_id")
    private AdvertisementPackageEntity currentPackage;

    @Column(name = "remaining_ads")
    private Integer remainingAds;

    @Column(name = "package_expiry_date")
    private LocalDate packageExpiryDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.emailVerified = false;
        this.phoneNumberVerified = false;

        if (this.role == null) {
            this.role = AppUserRole.values()[0];
        }
    }
    public void upgradePackage(AdvertisementPackageEntity newPackage) {
        this.currentPackage = newPackage;

        if (this.remainingAds == null) {
            this.remainingAds = 0;
        }

        this.remainingAds += newPackage.getMaxAds();

        this.packageExpiryDate = LocalDate.now().plusDays(newPackage.getDurationInDays());
    }

    public boolean canPostAd() {
        return this.remainingAds > 0 && (packageExpiryDate == null || LocalDate.now().isBefore(packageExpiryDate));
    }

    public void decrementAdCount() {
        if (canPostAd()) {
            this.remainingAds--;
        } else {
            throw new IllegalStateException("No remaining ads or package expired");
        }
    }

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private PersonalFengshuiEntity personalFengshui;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}



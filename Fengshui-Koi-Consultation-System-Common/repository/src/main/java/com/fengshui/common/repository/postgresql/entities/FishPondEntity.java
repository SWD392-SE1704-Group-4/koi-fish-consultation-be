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
@Table(name = "fish_pond")
public class FishPondEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pond_id")
    private UUID pondId;

    @Column(name = "pond_name", nullable = false, length = 100)
    private String pondName;

    @Column(name = "pond_shape", length = 50)
    private String pondShape;

    @Column(name = "pond_size")
    private Double pondSize;  // in square meters

    @Column(name = "pond_depth")
    private Double pondDepth;  // in meters

    @Column(name = "pond_material", length = 100)
    private String pondMaterial;

    @Column(name = "has_waterfall")
    private Boolean hasWaterfall;

    @Column(name = "has_plants")
    private Boolean hasPlants;

    @Column(name = "has_rocks")
    private Boolean hasRocks;

    @Column(name = "is_saltwater")
    private Boolean isSaltwater;

    @Column(name = "num_koi_fish")
    private Integer numKoiFish;   // Number of koi fish allowed in the pond

    @Column(name = "water_capacity")
    private Double waterCapacity;  // in liters or cubic meters

    @Column(name = "pond_element", length = 50)
    private String pondElement;   // Feng Shui element associated with the pond

    @Column(name = "pond_location", length = 50)
    private String pondLocation;  // Indoor or outdoor

    @Column(name = "pond_orientation", length = 50)
    private String pondOrientation; // Direction the pond is facing (based on Feng Shui)

    @ElementCollection
    @CollectionTable(name = "fish_pond_pictures", joinColumns = @JoinColumn(name = "pond_id"))
    @Column(name = "pond_picture")
    private List<String> pondPictures;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Automatically set the createdAt and updatedAt values
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}


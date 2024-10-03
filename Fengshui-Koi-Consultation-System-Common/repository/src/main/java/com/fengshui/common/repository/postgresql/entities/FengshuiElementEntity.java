package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "fengshui_element")
public class FengshuiElementEntity {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "element_id")
    private UUID Id;

    @Column(name = "element_name", nullable = false, length = 50)
    private String elementName;

    @Column(name = "element_color", length = 50)
    private String elementColor;

    @Column(name = "element_direction", length = 50)
    private String elementDirection;

    @Column(name = "element_season", length = 50)
    private String elementSeason;

    @Column(name = "element_yin_yang", length = 50)
    private String elementYinYang;

    @Column(name = "element_associated_animals", length = 255)
    private String elementAssociatedAnimals;

    @Column(name = "element_strength")
    private Integer elementStrength;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Optional: One-to-Many relationship with KoiFishEntity (for reverse lookup)
    @OneToMany(mappedBy = "fengshuiElement")
    private List<KoiFishEntity> koiFishList;

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

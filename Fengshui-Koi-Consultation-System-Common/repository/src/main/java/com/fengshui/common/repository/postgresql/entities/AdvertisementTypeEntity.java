package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "advertisement_type")
public class AdvertisementTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "advertisement_type_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "advertisementType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdvertisementEntity> advertisements;
}

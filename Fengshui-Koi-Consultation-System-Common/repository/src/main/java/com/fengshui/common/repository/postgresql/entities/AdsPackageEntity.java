package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "adspackage")
public class AdsPackageEntity {

    @Id
    @GeneratedValue
    @Column(name = "package_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "package-name", nullable = false)
    private String packageName;

    @Column(name = "package-price", nullable = false)
    private double packagePrice;

    @Column(name = "package-value", nullable = false)
    private double packageValue;

    @Column(name = "package-priority")
    private int packagePriority;
}

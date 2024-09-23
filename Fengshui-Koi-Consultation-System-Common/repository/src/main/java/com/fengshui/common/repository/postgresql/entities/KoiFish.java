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
@Table(name = "koi_fish")
public class KoiFish {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "koi_fish_id")
    private UUID Id;

    @Column(name = "koi_fish_name")
    private String koiFishName;

    @Column(name = "koi_fish_color")
    private String koiFishColor;

    @Column(name = "koi_fish_size")
    private double koiFishSize;

    @Column(name = "koi_fish_age")
    private double koiFishAge;
}

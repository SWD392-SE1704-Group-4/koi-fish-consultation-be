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
@Table(name = "personal_fengshui")
public class PersonalFengshuiEntity {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name = "personal_fengshui_id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "app_user_id")
    private AppUserEntity appUser;

    @Column(name = "earthly_branch", nullable = false)
    private String earthlyBranch;

    @Column(name = "element", nullable = false)
    private String element;

    @Column(name = "fish_recommendation", columnDefinition = "TEXT")
    private String fishRecommendation;

    @Column(name = "heavenly_stem", nullable = false)
    private String heavenlyStem;

    @Column(name = "tank_direction", nullable = false)
    private String tankDirection;
}

package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "fengshui_direction")
public class FengshuiDirectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "direction_id", updatable = false, nullable = false)
    private UUID directionId;

    @Column(name = "direction_name", nullable = false, length = 50)
    private String directionName;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "element_association", length = 50)
    private String elementAssociation;

    @Column(name = "yin_yang_balance", length = 50)
    private String yinYangBalance;

    @Column(name = "favorable_attributes", length = 255)
    private String favorableAttributes;

    @Column(name = "unfavorable_attributes", length = 255)
    private String unfavorableAttributes;

    @Column(name = "secondary_direction", length = 50)
    private String secondaryDirection;

    @Column(name = "fengshui_tips", length = 255)
    private String fengshuiTips;

    @Column(name = "associated_color", length = 50)
    private String associatedColor;

    @Column(name = "associated_shape", length = 50)
    private String associatedShape;

    @ManyToOne
    @JoinColumn(name = "element_id", referencedColumnName = "element_id")
    private FengshuiElementEntity fengshuiElement;
}

package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "koi_fish")
public class KoiFishEntity {
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

    // Store picture URLs or file paths
    @ElementCollection
    @CollectionTable(name = "koi_fish_pictures", joinColumns = @JoinColumn(name = "koi_fish_id"))
    @Column(name = "picture")
    private List<String> koiFishPictures;

    @ManyToOne
    @JoinColumn(name = "element_id", referencedColumnName = "element_id")
    private FengshuiElementEntity fengshuiElement;

    @OneToMany(mappedBy = "koiFish")
    private List<AdvertisementEntity> advertisementList;

    @Column(name = "symbolic_meaning")
    private String symbolicMeaning;

    @Column(name = "energy_type")
    private String energyType;

    @Column(name = "favorable_number")
    private int favorableNumber;

    @Column(name = "favorable_color")
    private String favorableColor;

    @Column(name = "koi_fish_origin")
    private String koiFishOrigin;

    @Column(name = "koi_fish_description")
    private String koiFishDescription;

    @Column(name = "koi_fish_price")
    private double koiFishPrice;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

}

package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.AdvertisementEntity;
import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.repository.postgresql.enums.AdvertisementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IAdvertisementRepository extends JpaRepository<AdvertisementEntity, UUID> {
    @Query("SELECT a FROM AdvertisementEntity a WHERE a.postedBy.id = :appUserId")
    List<AdvertisementEntity> findAdsByCreator(@Param("appUserId") UUID appUserId);
    @Query("""
    SELECT a FROM AdvertisementEntity a
    LEFT JOIN a.fishPond p
    LEFT JOIN a.koiFish k
    WHERE (k.fengshuiElement.Id = :elementId
           OR p.pondOrientation.directionId = :directionId)
    AND a.verified = true
""")
    List<AdvertisementEntity> findByFengShuiElementOrDirection(
            @Param("elementId") UUID elementId,
            @Param("directionId") UUID directionId
    );
    boolean existsByKoiFishAndStatus(KoiFishEntity koiFish, AdvertisementStatus status);
}

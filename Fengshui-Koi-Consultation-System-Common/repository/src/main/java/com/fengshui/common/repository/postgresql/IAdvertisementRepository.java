package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.AdvertisementEntity;
import com.fengshui.common.repository.postgresql.entities.FishPondEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAdvertisementRepository extends JpaRepository<AdvertisementEntity, UUID> {
    @Query("SELECT a FROM AdvertisementEntity a WHERE a.postedBy.id = :appUserId")
    List<AdvertisementEntity> findAdsByCreator(@Param("appUserId") UUID appUserId);
}

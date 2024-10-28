package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.FishPondEntity;
import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IFishPondRepository extends JpaRepository<FishPondEntity, UUID> {
    Optional<FishPondEntity> findById(UUID uuid);

    @Query("SELECT f FROM FishPondEntity f WHERE f.createdBy.id = :appUserId")
    List<FishPondEntity> findByCreator(@Param("appUserId") UUID appUserId);

}

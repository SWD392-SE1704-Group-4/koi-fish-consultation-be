package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.FishPondEntity;
import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IFishPondRepository extends JpaRepository<FishPondEntity, UUID> {
    Optional<FishPondEntity> findById(UUID uuid);
}

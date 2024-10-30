package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.HeavenlyEarthlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IHeavenlyEarthlyRepository extends JpaRepository<HeavenlyEarthlyEntity, UUID> {
}

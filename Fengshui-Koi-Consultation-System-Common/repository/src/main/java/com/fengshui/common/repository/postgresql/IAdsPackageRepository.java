package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.AdsPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAdsPackageRepository extends JpaRepository<AdsPackageEntity, UUID> {
}

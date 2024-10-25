package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.AdvertisementPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAdvertisementPackageRepository extends JpaRepository<AdvertisementPackageEntity, UUID> {
}

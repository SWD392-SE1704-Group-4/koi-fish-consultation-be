package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.AdvertisementTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAdvertisementTypeRepository extends JpaRepository<AdvertisementTypeEntity, UUID> {
}

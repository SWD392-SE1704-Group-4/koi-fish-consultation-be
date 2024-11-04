package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.AdvertisementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface IAdvertisementCustomRepository {
    public List<AdvertisementEntity> queryAdvertisement(String querySql, String keyword, List<UUID> categoryIds, Pageable pageable);
}

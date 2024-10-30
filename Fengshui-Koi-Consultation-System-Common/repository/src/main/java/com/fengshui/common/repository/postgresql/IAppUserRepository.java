package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.AppUserEntity;
import com.fengshui.common.repository.postgresql.entities.FishPondEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUserEntity, UUID> {
    Optional<AppUserEntity> findById(UUID uuid);
}

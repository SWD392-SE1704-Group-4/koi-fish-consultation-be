package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUserEntity, UUID> {
}

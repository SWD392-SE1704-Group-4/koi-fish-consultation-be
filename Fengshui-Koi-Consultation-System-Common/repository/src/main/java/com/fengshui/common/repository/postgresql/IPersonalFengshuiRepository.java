package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.AppUserEntity;
import com.fengshui.common.repository.postgresql.entities.PersonalFengshuiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPersonalFengshuiRepository extends JpaRepository<PersonalFengshuiEntity, UUID> {
    Optional<PersonalFengshuiEntity> findByAppUser(AppUserEntity appUser);
}

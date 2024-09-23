package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "app_user_id")
    private UUID Id;

    @Column(name = "app_user_name")
    private String appUsername;

    @Column(name = "app_user_account_password")
    private String appUserAccountPassword;

    @Column(name = "app_user_first_name")
    private String appUserFirstName;

    @Column(name = "app_user_last_name")
    private String appUserLastName;

    @Column(name = "app_user_email")
    private String appUserEmail;

    @Column(name = "app_user_phone")
    private String appUserPhone;

    @Column(name = "app_user_role")
    private String appUserRole;

    @Column(name = "registered_date", nullable = false)
    private LocalDateTime registeredDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;
}

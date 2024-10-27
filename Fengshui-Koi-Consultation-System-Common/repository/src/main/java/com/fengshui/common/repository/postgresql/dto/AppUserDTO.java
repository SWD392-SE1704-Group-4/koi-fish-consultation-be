package com.fengshui.common.repository.postgresql.dto;

import com.fengshui.common.repository.postgresql.enums.AppUserRole;
import com.fengshui.common.repository.postgresql.enums.AppUserStatus;
import com.fengshui.common.repository.postgresql.enums.GenderEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDTO {
    private UUID id;
    private String username;
    private String email;
    private Boolean emailVerified;
    private String phoneNumber;
    private Boolean phoneNumberVerified;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private GenderEnum gender;
    private String address;
    private String profilePictureUrl;
    private AppUserStatus status;
    private AppUserRole role;
    private LocalDate lastLogin;
    private List<AdvertisementDTO> advertisements;
    private List<FishPondDTO> fishPonds;
}

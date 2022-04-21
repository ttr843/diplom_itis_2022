package ru.itis.pashin.website.common.model.user.dto;

import lombok.*;
import ru.itis.pashin.website.common.model.catalogs.dto.BankDTO;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private UUID guid;

    private String phoneNumber;

    private Short age;

    private String country;

    private String city;

    private String street;

    private Short house;

    private String email;

    private RoleDTO role;

    private boolean isBlocked;

    private boolean isConfirmed;

    private String passwordEncrypted;

    private BankDTO bank;

    private String position;
}

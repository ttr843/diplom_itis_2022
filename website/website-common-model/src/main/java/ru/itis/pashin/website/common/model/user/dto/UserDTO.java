package ru.itis.pashin.website.common.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private UUID guid;

    private String phoneNumber;

    private Integer age;

    private String country;

    private String city;

    private String street;

    private Integer house;

    private String email;

    private RoleDTO role;

    private boolean isBlocked;
}

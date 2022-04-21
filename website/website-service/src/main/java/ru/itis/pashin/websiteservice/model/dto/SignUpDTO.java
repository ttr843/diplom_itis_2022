package ru.itis.pashin.websiteservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String middleName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String phoneNumber;

    private Short age;

    private String country;

    private String city;

    private String street;

    private Short house;

    @Email()
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16)
    private String password;

}

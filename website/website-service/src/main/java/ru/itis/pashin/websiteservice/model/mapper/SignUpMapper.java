package ru.itis.pashin.websiteservice.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.websiteservice.model.dto.SignUpDTO;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Mapper(componentModel = "spring")
public interface SignUpMapper {

    default UserDTO signUpDTOToUserDTO(SignUpDTO signUpDTO) {
        return UserDTO.builder()
                .firstName(signUpDTO.getFirstName())
                .middleName(signUpDTO.getMiddleName())
                .lastName(signUpDTO.getLastName())
                .phoneNumber(signUpDTO.getPhoneNumber())
                .age(signUpDTO.getAge())
                .country(signUpDTO.getCountry())
                .city(signUpDTO.getCity())
                .street(signUpDTO.getStreet())
                .house(signUpDTO.getHouse())
                .email(signUpDTO.getEmail())
                .build();
    }
}

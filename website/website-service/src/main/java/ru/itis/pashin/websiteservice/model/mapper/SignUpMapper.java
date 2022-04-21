package ru.itis.pashin.websiteservice.model.mapper;

import org.mapstruct.Mapper;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.websiteservice.model.dto.SignUpDTO;


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

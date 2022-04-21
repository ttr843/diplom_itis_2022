package ru.itis.pashin.websiteservice.model.mapper;

import org.mapstruct.Mapper;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.websiteservice.model.dto.CreateBankerDTO;


@Mapper(componentModel = "spring")
public interface CreateBankerMapper {

    default UserDTO createBankerDTOToUserDTO(CreateBankerDTO createBankerDTO) {
        return UserDTO.builder()
                .firstName(createBankerDTO.getFirstName())
                .middleName(createBankerDTO.getMiddleName())
                .lastName(createBankerDTO.getLastName())
                .phoneNumber(createBankerDTO.getPhoneNumber())
                .age(createBankerDTO.getAge())
                .country(createBankerDTO.getCountry())
                .city(createBankerDTO.getCity())
                .street(createBankerDTO.getStreet())
                .house(createBankerDTO.getHouse())
                .email(createBankerDTO.getEmail())
                .position(createBankerDTO.getPosition())
                .build();
    }
}

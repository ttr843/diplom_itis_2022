package ru.itis.pashin.website.common.model.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.website.common.model.user.entity.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    public RoleMapper roleMapper;

    @Mapping(target = "role", expression = "java(roleMapper.entityToDTO(user.getRole()))")
    public abstract UserDTO entityToDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(roleMapper.dtoToEntity(userDTO.getRole()))")
    public abstract User createUser(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    public abstract void updateUser(@MappingTarget User user, UserDTO userDTO);


}

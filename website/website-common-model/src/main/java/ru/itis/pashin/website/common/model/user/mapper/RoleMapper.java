package ru.itis.pashin.website.common.model.user.mapper;

import org.mapstruct.Mapper;
import ru.itis.pashin.website.common.model.user.dto.RoleDTO;
import ru.itis.pashin.website.common.model.user.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO entityToDTO(Role role);

    Role dtoToEntity(RoleDTO roleDTO);
}

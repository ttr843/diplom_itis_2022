package ru.itis.pashin.website.common.model.user.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final RoleMapper roleMapper;

//  public UserDTO entityToUserDTO(User user) {
//
//  }

//  public User dtoToEntity(UserDTO user) {
//    return User.builder()
//            .id(user.getId())
//            .username(user.getUsername())
//            .build();
//  }

}

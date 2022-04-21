package ru.itis.pashin.websiteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode;
import ru.itis.pashin.website.common.model.exception.exception.ServiceException;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.website.common.model.user.entity.User;
import ru.itis.pashin.website.common.model.user.mapper.RoleMapper;
import ru.itis.pashin.website.common.model.user.mapper.UserMapper;
import ru.itis.pashin.website.common.service.repository.RoleRepository;
import ru.itis.pashin.website.common.service.repository.UserRepository;
import ru.itis.pashin.websiteservice.security.details.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.ERROR_CODE_0005;
import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.ERROR_CODE_0006;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;
    private final ConfirmService confirmService;

    public UserDTO getCurrentUser(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userMapper.entityToDTO(userDetails.getUser());
    }

    public void completeUserDTO(UserDTO userDTO, String password, String role) {
        userDTO.setPasswordEncrypted(passwordEncoder.encode(password));
        userDTO.setRole(roleRepository.findRoleByCode(role).map(roleMapper::entityToDTO).orElse(null));
        userDTO.setBlocked(true);
        userDTO.setConfirmed(false);
        if (Objects.isNull(userDTO.getRole())) {
            throw new ServiceException(ServiceErrorCode.ERROR_CODE_0001, role);
        }
        User createdUser = userRepository.save(userMapper.createUser(userDTO));
        confirmService.createConfirmation(createdUser.getGuid(), createdUser.getEmail());
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::entityToDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    public void blockUser(Long id) {
        try {
            userRepository.blockUser(id);
        } catch (Exception e) {
            log.error("ошибка при блокировании УЗ:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0006);
        }
    }

    @Transactional
    public void unblockUser(Long id) {
        try {
            userRepository.unblockUser(id);
        } catch (Exception e) {
            log.error("ошибка при разблокировании УЗ:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0005);
        }
    }
}

package ru.itis.pashin.websiteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pashin.website.common.model.confirmation.dto.ConfirmationDTO;
import ru.itis.pashin.website.common.model.confirmation.mapper.ConfirmationMapper;
import ru.itis.pashin.website.common.model.service.enumeration.ServiceErrorCode;
import ru.itis.pashin.website.common.model.service.exception.ServiceException;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.website.common.model.user.entity.User;
import ru.itis.pashin.website.common.model.user.mapper.RoleMapper;
import ru.itis.pashin.website.common.model.user.mapper.UserMapper;
import ru.itis.pashin.website.common.service.repository.ConfirmationRepository;
import ru.itis.pashin.website.common.service.repository.RoleRepository;
import ru.itis.pashin.website.common.service.repository.UserRepository;
import ru.itis.pashin.websiteservice.model.dto.SignUpDTO;
import ru.itis.pashin.websiteservice.model.mapper.SignUpMapper;

import java.util.Objects;
import java.util.UUID;

import static ru.itis.pashin.website.common.model.service.enumeration.ServiceErrorCode.ERROR_CODE_0002;
import static ru.itis.pashin.website.common.model.service.enumeration.ServiceErrorCode.ERROR_CODE_0003;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private static final String USER_ROLE_CODE = "USER";
    private final SignUpMapper signUpMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ConfirmationRepository confirmationRepository;
    private final ConfirmationMapper confirmationMapper;

    @Transactional
    public void signUp(SignUpDTO signUpDTO) {
        try {
            UserDTO userDTO = signUpMapper.signUpDTOToUserDTO(signUpDTO);
            userDTO.setPasswordEncrypted(passwordEncoder.encode(signUpDTO.getPassword()));
            userDTO.setRole(roleRepository.findRoleByCode(USER_ROLE_CODE).map(roleMapper::entityToDTO).orElse(null));
            userDTO.setBlocked(true);
            userDTO.setConfirmed(false);
            if (Objects.isNull(userDTO.getRole())) {
                throw new ServiceException(ServiceErrorCode.ERROR_CODE_0001, USER_ROLE_CODE);
            }

            User createdUser = userRepository.save(userMapper.createUser(userDTO));
            createConfirmation(createdUser.getGuid(), createdUser.getEmail());
        } catch (Exception e) {
            log.error("ошибка при создании УЗ:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0002);
        }
    }


    private void createConfirmation(UUID userGuid, String userEmail) {
        try {
            confirmationRepository.save(confirmationMapper.create(ConfirmationDTO.builder()
                    .confirmCode(userGuid)
                    .email(userEmail)
                    .build()));
        } catch (Exception e) {
            log.error("ошибка при создании записи о потверждении учетной записи: {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0003);
        }
    }
}

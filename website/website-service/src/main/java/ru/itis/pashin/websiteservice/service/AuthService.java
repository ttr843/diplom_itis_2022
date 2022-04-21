package ru.itis.pashin.websiteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pashin.website.common.model.exception.exception.ServiceException;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.websiteservice.model.dto.SignUpDTO;
import ru.itis.pashin.websiteservice.model.mapper.SignUpMapper;

import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.ERROR_CODE_0002;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private static final String USER_ROLE_CODE = "USER";
    private final SignUpMapper signUpMapper;
    private final UserService userService;

    @Transactional
    public void signUp(SignUpDTO signUpDTO) {
        try {
            UserDTO userDTO = signUpMapper.signUpDTOToUserDTO(signUpDTO);
            userService.completeUserDTO(userDTO, signUpDTO.getPassword(), USER_ROLE_CODE);
        } catch (Exception e) {
            log.error("ошибка при создании УЗ:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0002);
        }
    }
}

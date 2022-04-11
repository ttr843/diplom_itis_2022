package ru.itis.pashin.websiteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.model.service.enumeration.ServiceErrorCode;
import ru.itis.pashin.website.common.model.service.exception.ServiceException;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.website.common.model.user.mapper.UserMapper;
import ru.itis.pashin.website.common.service.repository.UserRepository;

import java.util.UUID;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public boolean confirm(UUID guid) {
        userRepository.findUserByGuid(guid).ifPresentOrElse(user -> {
            UserDTO userDTO = userMapper.entityToDTO(user);
            userDTO.setConfirmed(true);
            userDTO.setBlocked(false);
            userMapper.updateUser(user, userDTO);
            userRepository.save(user);
        }, () -> {
            throw new ServiceException(ServiceErrorCode.ERROR_CODE_0000);
        });
        return true;
    }
}

package ru.itis.pashin.websiteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.model.confirmation.dto.ConfirmationDTO;
import ru.itis.pashin.website.common.model.confirmation.mapper.ConfirmationMapper;
import ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode;
import ru.itis.pashin.website.common.model.exception.exception.ServiceException;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.website.common.model.user.mapper.UserMapper;
import ru.itis.pashin.website.common.service.repository.ConfirmationRepository;
import ru.itis.pashin.website.common.service.repository.UserRepository;

import java.util.UUID;

import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.ERROR_CODE_0003;


@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ConfirmationRepository confirmationRepository;
    private final ConfirmationMapper confirmationMapper;

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


    public void createConfirmation(UUID userGuid, String userEmail) {
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

package ru.itis.pashin.mail.integration.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pashin.website.common.model.confirmation.dto.ConfirmationDTO;
import ru.itis.pashin.website.common.model.confirmation.entity.Confirmation;
import ru.itis.pashin.website.common.model.confirmation.mapper.ConfirmationMapper;
import ru.itis.pashin.website.common.service.repository.ConfirmationRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@ConditionalOnExpression("${sender.integration.receive-polling-interval:1000} > 0")
@Slf4j
@RequiredArgsConstructor
public class ReceiveConfirmationService {

    private static final String FIXED_DELAY = "${sender.integration.receive-polling-interval:1000}";
    private final ConfirmationRepository confirmationRepository;
    private final ConfirmationMapper confirmationMapper;
    private final MailSenderService mailSenderService;

    @Scheduled(fixedDelayString = FIXED_DELAY)
    public void checkReceive() {
        log.debug("get new confirmations");
        StreamSupport.stream(
                confirmationRepository.findAllBySendOrderByCreatedAsc(false).spliterator(), false)
                .collect(Collectors.toList())
                .forEach(this::handleConfirmation);
    }

    @Transactional
    public void handleConfirmation(Confirmation confirmation) {
        ConfirmationDTO confirmationDTO = confirmationMapper.entityToDTO(confirmation);
        try {
            mailSenderService.sendConfirmMessage(confirmationDTO);
            confirmationDTO.setSend(true);
            confirmationDTO.setSendTime(LocalDateTime.now(ZoneOffset.UTC));
            confirmationMapper.update(confirmation, confirmationDTO);
            confirmationRepository.save(confirmation);
        } catch (Exception e) {
            log.error("Ошибка при отправке сообщения для потверждения учетной записи, уникальный код : {}, ошибка : {}",
                    confirmationDTO.getConfirmCode(), e.getMessage());
        }
    }
}

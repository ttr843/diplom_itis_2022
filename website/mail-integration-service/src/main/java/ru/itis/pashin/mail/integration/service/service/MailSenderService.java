package ru.itis.pashin.mail.integration.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.model.confirmation.dto.ConfirmationDTO;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${sender.integration.host-confirm-url}")
    private String hostname;

    public boolean sendLoginMessage(String email) {
        log.info("Отправлено сообщение о входе на почту: {}", email);
        sendMail("В Ваш аккаунт выполнен вход.", "В ваш аккаунт был выполнен вход. Если это не вы, сообщите нам, ответив на это письмо", email);
        return true;
    }

    public void sendConfirmMessage(ConfirmationDTO confirmationDTO) {
        sendMail("Добрый день. Подтвердите учетную запись.", prepareConfirmText(confirmationDTO.getConfirmCode()), confirmationDTO.getEmail());
    }

    private String prepareConfirmText(UUID confirmCode) {
        return "<a href=" +
                hostname +
                confirmCode.toString() +
                " target=_blank><b>Подтвердить почту!</b></a>";
    }


    private void sendMail(String subject, String text, String email) {
        send(subject, text, email);
    }

    private void send(String subject, String text, String email) {
        MimeMessagePreparator messagePreparatory = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(userName);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        };
        javaMailSender.send(messagePreparatory);
    }


}

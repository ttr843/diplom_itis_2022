package ru.itis.pashin.websiteservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.websiteservice.service.LoanService;
import ru.itis.pashin.websiteservice.util.KafkaTopicConstant;

import java.util.Collections;
import java.util.Objects;

import static ru.itis.pashin.websiteservice.config.KafkaConfiguration.LOAN_RESPONSE_GROUP;
import static ru.itis.pashin.websiteservice.config.KafkaConfiguration.MAX_POLL_INTERVAL_MS;

@Slf4j
@Component
@RequiredArgsConstructor
public class MlResponseKafkaListener {

    private final LoanService loanService;

    @KafkaListener(topics = KafkaTopicConstant.LOAN_RESPONSE_TOPIC, groupId = LOAN_RESPONSE_GROUP, properties = {
            MAX_POLL_INTERVAL_MS
    })
    public void listenResponse(LoanApplicationDTO loanApplicationDTO) {
        if (Objects.isNull(loanApplicationDTO)) {
            log.debug("заявка пустая");
            return;
        }
        log.debug("начинаю обрабатывать заявку");
        Long loanId = loanApplicationDTO.getId();
        log.info("listenResponse() - start. loanId: {}", loanId);
        try {
            loanService.afterLoanSaveOperation(loanApplicationDTO, Collections.singletonList(KafkaTopicConstant.LOAN_INDEX_WRITE_TOPIC));
            log.info("listenResponse() - end.  loanId: {}", loanId);
        } catch (Exception e) {
            log.error("listenResponse() - error. loanId: " + loanId + "\n" + e.getMessage(), e);
        }
    }
}

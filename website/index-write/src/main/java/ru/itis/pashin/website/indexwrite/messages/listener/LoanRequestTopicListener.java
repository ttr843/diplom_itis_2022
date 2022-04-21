package ru.itis.pashin.website.indexwrite.messages.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.indexwrite.messages.config.KafkaConfiguration;
import ru.itis.pashin.website.indexwrite.messages.util.KafkaTopicConstant;
import ru.itis.pashin.website.indexwrite.service.IndexService;

import java.util.Objects;


@Slf4j
@Component
@RequiredArgsConstructor
public class LoanRequestTopicListener {

    private final IndexService indexService;

    @KafkaListener(topics = KafkaTopicConstant.LOAN_INDEX_WRITE_TOPIC, groupId = KafkaConfiguration.LOAN_REQUEST_GROUP, properties = {
            KafkaConfiguration.MAX_POLL_INTERVAL_MS
    })
    public void listen(LoanApplicationDTO loanApplicationDTO) {
        if (Objects.isNull(loanApplicationDTO)) {
            log.debug("заявка пустая");
            return;
        }
        try {
            indexService.save(loanApplicationDTO);
            log.info("сохранено {}: {}", KafkaTopicConstant.LOAN_INDEX_WRITE_TOPIC, loanApplicationDTO.getId());
        } catch (Exception e) {
            log.error("ошибка сохранения {}: {}", KafkaTopicConstant.LOAN_INDEX_WRITE_TOPIC, loanApplicationDTO.getId(), e);
            throw e;
        }
    }

}

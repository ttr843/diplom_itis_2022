package ru.itis.pashin.mlservice.messages.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.itis.pashin.mlservice.messages.util.KafkaTopicConstant;
import ru.itis.pashin.mlservice.service.LoanMlPredictService;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.common.model.loan.entity.LoanApplication;
import ru.itis.pashin.website.common.model.loan.enumeration.MlStatus;
import ru.itis.pashin.website.common.model.loan.mapper.LoanApplicationMapper;
import ru.itis.pashin.website.common.service.repository.LoanApplicationRepository;

import java.util.Objects;

import static ru.itis.pashin.mlservice.messages.config.KafkaConfiguration.LOAN_REQUEST_GROUP;
import static ru.itis.pashin.mlservice.messages.config.KafkaConfiguration.MAX_POLL_INTERVAL_MS;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoanRequestTopicListener {

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanMlPredictService loanMlPredictService;

    @KafkaListener(topics = KafkaTopicConstant.LOAN_REQUEST_TOPIC, groupId = LOAN_REQUEST_GROUP, properties = {
            MAX_POLL_INTERVAL_MS
    })
    public void listen(LoanApplicationDTO loanApplicationDTO) {
        if (Objects.isNull(loanApplicationDTO)) {
            log.debug("заявка пустая");
            return;
        }
        log.debug("начинаю обрабатывать заявку");
        Long loanId = loanApplicationDTO.getId();
        log.info("listen() - start. loanId: {}", loanId);
        LoanApplication loanApplication = loanApplicationRepository.findById(loanId).orElse(null);
        if (Objects.isNull(loanApplication)) {
            log.warn("listenExportsTopic() - end. Заявка не обработано, скорее всего она была уже удалены. loanId: {}", loanId);
            return;
        }
        try {
            loanApplicationRepository.setStatus(loanId, MlStatus.IN_PROGRESS);
            LoanApplication updatedLoanApplication = loanMlPredictService.predict(loanApplication);
            loanApplicationRepository.save(updatedLoanApplication);
            log.info("listenExportsTopic() - end.  loanId: {}, status: {}", loanId, updatedLoanApplication.getMlStatus().name());
        } catch (Exception e) {
            loanApplicationRepository.setStatus(loanId, MlStatus.ERROR);
            log.error("listenExportsTopic() - error. loanId: " + loanApplication + "\n" + e.getMessage(), e);
        }
    }

}

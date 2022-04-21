package ru.itis.pashin.mlservice.messages.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.itis.pashin.mlservice.messages.producer.MLResponseProducer;
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


@Slf4j
@Component
@RequiredArgsConstructor
public class LoanRequestTopicListener {

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanMlPredictService loanMlPredictService;
    private final MLResponseProducer mlResponseProducer;
    private final LoanApplicationMapper loanApplicationMapper;

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
        LoanApplicationDTO updatedLoanApplicationDTO = null;
        try {
            updatedLoanApplicationDTO = processLoan(loanId);
            log.info("listenLoanRequestTopic() - end.  loanId: {}", loanId);
        } catch (Exception e) {
            handleError(loanId);
            log.error("listenLoanRequestTopic() - error. loanId: " + loanId + "\n" + e.getMessage(), e);
        }
        mlResponseProducer.sendLoan(updatedLoanApplicationDTO, KafkaTopicConstant.LOAN_RESPONSE_TOPIC);
    }

    private void handleError(Long loanId) {
        loanApplicationRepository.setStatus(loanId, MlStatus.ERROR);
    }

    private LoanApplicationDTO processLoan(Long loanId) {
        LoanApplication loanApplication = loanApplicationRepository.findById(loanId).orElse(null);
        if (Objects.isNull(loanApplication)) {
            log.warn("listenLoanRequestTopic() - end. Заявка не обработана, скорее всего она была уже удалены. loanId: {}", loanId);
            return null;
        }
        loanApplicationRepository.setStatus(loanId, MlStatus.IN_PROGRESS);
        LoanApplicationDTO updatedLoanApplicationDTO = loanMlPredictService.predict(loanApplicationMapper.entityToDTO(loanApplication));
        loanApplicationMapper.updateLoan(loanApplication, updatedLoanApplicationDTO);
        loanApplicationRepository.save(loanApplication);
        log.info("processLoan() - end.  loanId: {}, status: {}", loanId, updatedLoanApplicationDTO.getMlStatus().name());
        return updatedLoanApplicationDTO;
    }

}

package ru.itis.pashin.websiteservice.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.websiteservice.exception.LoanErrorCode;
import ru.itis.pashin.websiteservice.exception.LoanException;
import ru.itis.pashin.websiteservice.util.KafkaTopicConstant;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoanKafkaProducer {


    private final KafkaTemplate<String, LoanApplicationDTO> loanApplicationDTOKafkaTemplate;

    public void sendLoan(LoanApplicationDTO loanApplicationDTO) {
        ListenableFuture<SendResult<String, LoanApplicationDTO>> future =
                loanApplicationDTOKafkaTemplate.send(KafkaTopicConstant.LOAN_REQUEST_TOPIC, loanApplicationDTO);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, LoanApplicationDTO> result) {
                log.info("Заявка отправлена в кафку. идентификатор заявки: {}, offset: {}", loanApplicationDTO.getId(),
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                log.error("Получена ошибка при отправке заявки в кафку. exportId: " + loanApplicationDTO.getId() + "\n"
                        + e.getMessage(), e);
                throw new LoanException(LoanErrorCode.KAFKA_LOAN_SENDING_ERROR, e);
            }
        });
    }
}

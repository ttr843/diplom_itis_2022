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
import ru.itis.pashin.website.common.model.loan.exception.LoanErrorCode;
import ru.itis.pashin.website.common.model.loan.exception.LoanException;


@Slf4j
@Component
@RequiredArgsConstructor
public class LoanKafkaProducer {


    private final KafkaTemplate<String, LoanApplicationDTO> loanApplicationDTOKafkaTemplate;

    public void sendLoan(LoanApplicationDTO loanApplicationDTO, String topicName) {
        ListenableFuture<SendResult<String, LoanApplicationDTO>> future =
                loanApplicationDTOKafkaTemplate.send(topicName, loanApplicationDTO);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, LoanApplicationDTO> result) {
                log.info("Заявка отправлена в кафку. Топик {}. идентификатор заявки: {}, offset: {}",
                        topicName, loanApplicationDTO.getId(), result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                log.error("Получена ошибка при отправке заявки в кафку. Топик {}, идентификатор заявки {}, ошибка {}",
                        topicName, loanApplicationDTO.getId(), e.getMessage());
                throw new LoanException(LoanErrorCode.KAFKA_LOAN_SENDING_ERROR, e);
            }
        });
    }
}

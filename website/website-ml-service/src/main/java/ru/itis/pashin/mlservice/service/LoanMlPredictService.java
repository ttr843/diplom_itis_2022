package ru.itis.pashin.mlservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.common.model.loan.enumeration.MlStatus;


@Service
@Slf4j
@RequiredArgsConstructor
public class LoanMlPredictService {

    public LoanApplicationDTO predict(LoanApplicationDTO loanApplicationDTO) {
        int randomNumber = (int) (Math.random() + 1);
        if (randomNumber == 1) {
            loanApplicationDTO.setMlStatus(MlStatus.DONE_NOT_APPROVED);
        } else {
            loanApplicationDTO.setMlStatus(MlStatus.DONE_APPROVED);
        }
        return loanApplicationDTO;
    }
}

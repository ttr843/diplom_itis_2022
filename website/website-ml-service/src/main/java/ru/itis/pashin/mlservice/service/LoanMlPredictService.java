package ru.itis.pashin.mlservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.common.model.loan.entity.LoanApplication;
import ru.itis.pashin.website.common.model.loan.enumeration.MlStatus;
import ru.itis.pashin.website.common.model.loan.mapper.LoanApplicationMapper;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoanMlPredictService {

    private final LoanApplicationMapper loanApplicationMapper;

    public LoanApplication predict(LoanApplication loanApplication) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationMapper.entityToDTO(loanApplication);
        int randomNumber = (int) (Math.random() + 1);
        if(randomNumber == 1) {
            loanApplicationDTO.setMlStatus(MlStatus.DONE_NOT_APPROVED);
        } else {
            loanApplicationDTO.setMlStatus(MlStatus.DONE_APPROVED);
        }
        loanApplicationMapper.updateLoan(loanApplication,loanApplicationDTO);
        return loanApplication;
    }
}

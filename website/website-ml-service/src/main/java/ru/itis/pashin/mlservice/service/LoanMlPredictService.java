package ru.itis.pashin.mlservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.pashin.mlservice.client.MlPredictClient;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.common.model.loan.enumeration.MlStatus;


@Service
@Slf4j
@RequiredArgsConstructor
public class LoanMlPredictService {

    private final MlPredictClient mlPredictClient;

    public LoanApplicationDTO predict(LoanApplicationDTO loanApplicationDTO) {
        String result = mlPredictClient.predict(
                loanApplicationDTO.getIndustry().getId(),
                loanApplicationDTO.getCreditLimit(),
                loanApplicationDTO.getAmountOfLawsuits(),
                loanApplicationDTO.getAmountOfProceedings(),
                loanApplicationDTO.getCompanySizeType().getId(),
                loanApplicationDTO.getAmountOfWorkers(),
                loanApplicationDTO.getCapital(),
                loanApplicationDTO.getRevenue(),
                loanApplicationDTO.getNetProfit());
        if (result.equals("1")) {
            loanApplicationDTO.setMlStatus(MlStatus.DONE_APPROVED);
        } else {
            loanApplicationDTO.setMlStatus(MlStatus.DONE_NOT_APPROVED);
        }
        return loanApplicationDTO;
    }
}

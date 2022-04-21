package ru.itis.pashin.websiteservice.model.mapper;

import org.mapstruct.Mapper;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.websiteservice.model.dto.CreateLoanApplicationDTO;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Mapper(componentModel = "spring")
public interface CreateLoanApplicationMapper {

    default LoanApplicationDTO createLoanApplicationDTOToLoanApplicationDTO(CreateLoanApplicationDTO createLoanApplicationDTO) {
        return LoanApplicationDTO.builder()
                .companyName(createLoanApplicationDTO.getCompanyName())
                .companyInn(createLoanApplicationDTO.getCompanyInn())
                .companyOgrn(createLoanApplicationDTO.getCompanyOgrn())
                .creditLimit(createLoanApplicationDTO.getCreditLimit())
                .amountOfLawsuits(createLoanApplicationDTO.getAmountOfLawsuits())
                .amountOfProceedings(createLoanApplicationDTO.getAmountOfProceedings())
                .amountOfWorkers(createLoanApplicationDTO.getAmountOfWorkers())
                .capital(createLoanApplicationDTO.getCapital())
                .revenue(createLoanApplicationDTO.getRevenue())
                .netProfit(createLoanApplicationDTO.getNetProfit())
                .build();
    }
}

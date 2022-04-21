package ru.itis.pashin.websiteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.model.Util.DateFormatConstants;
import ru.itis.pashin.website.common.model.catalogs.mapper.CompanySizeTypeMapper;
import ru.itis.pashin.website.common.model.catalogs.mapper.IndustryMapper;
import ru.itis.pashin.website.common.model.exception.exception.ServiceException;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.common.model.loan.entity.LoanApplication;
import ru.itis.pashin.website.common.model.loan.enumeration.MlStatus;
import ru.itis.pashin.website.common.model.loan.mapper.LoanApplicationMapper;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.website.common.service.repository.CompanySizeTypeRepository;
import ru.itis.pashin.website.common.service.repository.IndustryRepository;
import ru.itis.pashin.website.common.service.repository.LoanApplicationRepository;
import ru.itis.pashin.websiteservice.model.dto.CreateLoanApplicationDTO;
import ru.itis.pashin.websiteservice.model.mapper.CreateLoanApplicationMapper;
import ru.itis.pashin.websiteservice.producer.LoanKafkaProducer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.*;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

    private static final String USER_ROLE_CODE = "USER";
    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanApplicationMapper loanApplicationMapper;
    private final CreateLoanApplicationMapper createLoanApplicationMapper;
    private final LoanKafkaProducer loanKafkaProducer;
    private final CompanySizeTypeRepository companySizeTypeRepository;
    private final CompanySizeTypeMapper companySizeTypeMapper;
    private final IndustryRepository industryRepository;
    private final IndustryMapper industryMapper;

    public List<LoanApplicationDTO> getLoans(UserDTO userDTO) {
        if (userDTO.getRole().getCode().equals(USER_ROLE_CODE)) {
            return StreamSupport.stream(
                    loanApplicationRepository.findAllByClientIdOrderByCreatedAt(userDTO.getId()).spliterator(), false)
                    .map(loanApplicationMapper::entityToDTO)
                    .collect(Collectors.toList());
        } else {
            return StreamSupport.stream(
                    loanApplicationRepository.findAllByMlStatusOrderByCreatedAt(MlStatus.DONE_APPROVED).spliterator(), false)
                    .map(loanApplicationMapper::entityToDTO)
                    .collect(Collectors.toList());
        }
    }

    public void createLoan(CreateLoanApplicationDTO createLoanApplicationDTO, UserDTO userDTO) {
        try {
            LoanApplicationDTO loanApplicationDTO = createLoanApplicationMapper.createLoanApplicationDTOToLoanApplicationDTO(createLoanApplicationDTO);
            loanApplicationDTO.setClient(userDTO);
            loanApplicationDTO.setMlStatus(MlStatus.NEW);
            loanApplicationDTO.setIndustry(industryMapper.entityToDTO(
                    industryRepository.findById(createLoanApplicationDTO.getIndustryId())
                            .orElseThrow(() -> new ServiceException(ERROR_CODE_0009))));
            loanApplicationDTO.setCompanySizeType(companySizeTypeMapper.entityToDTO(
                    companySizeTypeRepository.findById(createLoanApplicationDTO.getCompanySizeTypeId())
                            .orElseThrow(() -> new ServiceException(ERROR_CODE_0010))));
            loanApplicationDTO.setCreatedAt(LocalDateTime.now().format(DateFormatConstants.DATE_TIME_FORMAT));
            LoanApplication loanApplication = loanApplicationMapper.createLoan(loanApplicationDTO);
            LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
            loanKafkaProducer.sendLoan(loanApplicationMapper.entityToDTO(savedLoanApplication));
        } catch (Exception e) {
            log.error("ошибка при создании заявки:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0008);
        }

    }

    public List<LoanApplicationDTO> getAllLoans() {
        return loanApplicationRepository.findAll().stream()
                .map(loanApplicationMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public void approveLoan(UUID guid, UserDTO currentUser) {
        try {
            LoanApplication loanApplication = loanApplicationRepository.findByGuid(guid)
                    .orElseThrow(() -> new ServiceException(ERROR_CODE_0011));
            LoanApplicationDTO loanApplicationDTO = loanApplicationMapper.entityToDTO(loanApplication);
            if (!Objects.equals(loanApplicationDTO.getMlStatus(), MlStatus.DONE_APPROVED)) {
                log.error("заявка еще не одобрена системой, идентификатор заявки {}", loanApplicationDTO.getId());
                throw new ServiceException(ERROR_CODE_0013);
            }
            loanApplicationDTO.setApprovedByBank(true);
            loanApplicationDTO.setApprovedByBankAt(LocalDateTime.now().format(DateFormatConstants.DATE_TIME_FORMAT));
            loanApplicationDTO.setBanker(currentUser);
            loanApplicationMapper.updateLoan(loanApplication, loanApplicationDTO);
            loanApplicationRepository.save(loanApplication);
        } catch (Exception e) {
            log.error("ошибка при одобрении заявки:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0012);
        }
    }
}

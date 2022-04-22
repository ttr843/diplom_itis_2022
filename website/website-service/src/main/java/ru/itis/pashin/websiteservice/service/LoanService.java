package ru.itis.pashin.websiteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import ru.itis.pashin.website.index.common.index.LoanIndex;
import ru.itis.pashin.websiteservice.client.IndexReadClient;
import ru.itis.pashin.websiteservice.model.dto.CreateLoanApplicationDTO;
import ru.itis.pashin.websiteservice.model.mapper.CreateLoanApplicationMapper;
import ru.itis.pashin.websiteservice.producer.LoanKafkaProducer;
import ru.itis.pashin.websiteservice.util.KafkaTopicConstant;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.*;


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
    private final IndexReadClient indexReadClient;


    public List<LoanApplicationDTO> getAllLoans() {
        return loanApplicationRepository.findAll().stream()
                .map(loanApplicationMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<LoanIndex> getLoans(UserDTO userDTO) {
        if (userDTO.getRole().getCode().equals(USER_ROLE_CODE)) {
            return StreamSupport.stream(
                    indexReadClient.findLoansByCreatedBy(userDTO.getId()).spliterator(), false)
                    .collect(Collectors.toList());
        } else {
            return StreamSupport.stream(
                    indexReadClient.findLoansByMlStatus(MlStatus.DONE_APPROVED.getId()).spliterator(), false)
                    .collect(Collectors.toList());
        }
    }

    public void saveLoan(CreateLoanApplicationDTO createLoanApplicationDTO, UserDTO currentUser) {
        try {
            LoanApplication createdLoan = createLoan(createLoanApplicationDTO, currentUser);
            LoanApplicationDTO createdLoanDTO = loanApplicationMapper.entityToDTO(createdLoan);
            afterLoanSaveOperation(createdLoanDTO, Arrays.asList(KafkaTopicConstant.LOAN_REQUEST_TOPIC, KafkaTopicConstant.LOAN_INDEX_WRITE_TOPIC));
        } catch (Exception e) {
            log.error("ошибка при создании заявки:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0008);
        }

    }

    public void approveByGuid(UUID guid, UserDTO currentUser) {
        try {
            LoanApplicationDTO approvedLoanApplicationDTO = approveLoan(guid, currentUser);
            afterLoanSaveOperation(approvedLoanApplicationDTO, Collections.singletonList(KafkaTopicConstant.LOAN_INDEX_WRITE_TOPIC));
        } catch (Exception e) {
            log.error("ошибка при одобрении заявки:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0012);
        }
    }

    private LoanApplication createLoan(CreateLoanApplicationDTO createLoanApplicationDTO, UserDTO currentUser) {
        LoanApplicationDTO loanApplicationDTO = createLoanApplicationMapper.createLoanApplicationDTOToLoanApplicationDTO(createLoanApplicationDTO);
        loanApplicationDTO.setClient(currentUser);
        loanApplicationDTO.setMlStatus(MlStatus.NEW);
        loanApplicationDTO.setIndustry(industryMapper.entityToDTO(
                industryRepository.findById(Long.valueOf(createLoanApplicationDTO.getIndustryId().replace("\u00A0", StringUtils.EMPTY)))
                        .orElseThrow(() -> new ServiceException(ERROR_CODE_0009))));
        loanApplicationDTO.setCompanySizeType(companySizeTypeMapper.entityToDTO(
                companySizeTypeRepository.findById(createLoanApplicationDTO.getCompanySizeTypeId())
                        .orElseThrow(() -> new ServiceException(ERROR_CODE_0010))));
        loanApplicationDTO.setCreatedAt(LocalDateTime.now().format(DateFormatConstants.DATE_TIME_FORMAT));
        LoanApplication loanApplication = loanApplicationMapper.createLoan(loanApplicationDTO);
        return loanApplicationRepository.save(loanApplication);
    }

    private LoanApplicationDTO approveLoan(UUID guid, UserDTO currentUser) {
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
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return loanApplicationMapper.entityToDTO(savedLoanApplication);
    }

    public void afterLoanSaveOperation(LoanApplicationDTO loanApplicationDTO, List<String> topics) {
        for (String topic : topics) {
            loanKafkaProducer.sendLoan(loanApplicationDTO, topic);
        }
    }
}

package ru.itis.pashin.websiteservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pashin.website.common.model.catalogs.entity.Bank;
import ru.itis.pashin.website.common.model.catalogs.mapper.BankMapper;
import ru.itis.pashin.website.common.model.exception.exception.ServiceException;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.website.common.service.repository.BankRepository;
import ru.itis.pashin.websiteservice.model.dto.CreateBankerDTO;
import ru.itis.pashin.websiteservice.model.mapper.CreateBankerMapper;

import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.ERROR_CODE_0002;
import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.ERROR_CODE_0014;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private static final String BANKER_ROLE_CODE = "BANK_EMPLOYEE";
    private final CreateBankerMapper createBankerMapper;
    private final UserService userService;
    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    @Transactional
    public void createBanker(CreateBankerDTO createBankerDTO) {
        try {
            UserDTO userDTO = createBankerMapper.createBankerDTOToUserDTO(createBankerDTO);
            Bank bank = bankRepository.findById(createBankerDTO.getBankId()).orElseThrow(() -> new ServiceException(ERROR_CODE_0014));
            userDTO.setBank(bankMapper.entityToDTO(bank));
            userService.completeUserDTO(userDTO, createBankerDTO.getPassword(), BANKER_ROLE_CODE);
        } catch (Exception e) {
            log.error("ошибка при создании УЗ:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0002);
        }
    }
}

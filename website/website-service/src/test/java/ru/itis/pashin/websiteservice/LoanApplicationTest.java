package ru.itis.pashin.websiteservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.common.model.loan.entity.LoanApplication;
import ru.itis.pashin.website.common.model.loan.mapper.LoanApplicationMapper;
import ru.itis.pashin.website.common.model.user.dto.RoleDTO;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.website.common.service.repository.LoanApplicationRepository;
import ru.itis.pashin.websiteservice.model.dto.CreateLoanApplicationDTO;
import ru.itis.pashin.websiteservice.model.mapper.CreateLoanApplicationMapper;
import ru.itis.pashin.websiteservice.service.LoanService;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoanApplicationTest {
    @Autowired
    LoanService loanService;
    @Autowired
    LoanApplicationMapper loanApplicationMapper;
    @Autowired
    CreateLoanApplicationMapper createLoanApplicationMapper;
    @MockBean
    LoanApplicationRepository loanApplicationRepository;
    private CreateLoanApplicationDTO createLoanApplicationDTO = null;
    private LoanApplication loanApplication = null;
    private UserDTO userDTO = null;

    @BeforeEach
    public void setUp() {
        this.createLoanApplicationDTO = CreateLoanApplicationDTO.builder()
                .companyName("companyName")
                .companyInn("companyInn")
                .companyOgrn("companyOgrn")
                .industryId("industryId")
                .creditLimit(10000L)
                .amountOfLawsuits(10000L)
                .amountOfProceedings(10000L)
                .amountOfWorkers(10000L)
                .capital(10000L)
                .revenue(10000L)
                .netProfit(10000L)
                .companySizeTypeId(10000L)
                .build();
        this.userDTO = UserDTO.builder()
                .id(1L)
                .firstName("firstName")
                .middleName("middleName")
                .lastName("lastName")
                .guid(UUID.randomUUID())
                .phoneNumber("phoneNumber")
                .age((short) 10)
                .country("country")
                .city("city")
                .street("street")
                .house((short) 10)
                .email("email@email.mail")
                .role(RoleDTO.builder()
                        .id(1L)
                        .code("USER")
                        .name("Пользователь")
                        .build())
                .isBlocked(false)
                .isConfirmed(true)
                .passwordEncrypted(UUID.randomUUID().toString())
                .build();
        this.loanApplication = loanApplicationMapper.createLoan(
                createLoanApplicationMapper.createLoanApplicationDTOToLoanApplicationDTO(createLoanApplicationDTO));
    }

    @Test
    void createLoan() {
        Mockito.doReturn(loanApplication)
                .when(loanApplicationRepository)
                .save(ArgumentMatchers.any());
        LoanApplication createdLoan = loanService.saveLoan(this.createLoanApplicationDTO, this.userDTO);
        Assertions.assertEquals(createdLoan.getCompanyName(), loanApplication.getCompanyName());
    }

    @Test
    void testApprove() {
        Mockito.doReturn(loanApplication)
                .when(loanApplicationRepository)
                .findByGuid(ArgumentMatchers.any(UUID.class));
        LoanApplicationDTO loanApplicationDTO = loanService.approveByGuid(UUID.randomUUID(), this.userDTO);
        Assertions.assertTrue(loanApplicationDTO.isApprovedByBank());
    }
}

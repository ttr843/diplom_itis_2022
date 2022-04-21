package ru.itis.pashin.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.pashin.website.common.model.exception.exception.ServiceException;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.websiteservice.model.dto.CreateLoanApplicationDTO;
import ru.itis.pashin.websiteservice.service.CatalogsService;
import ru.itis.pashin.websiteservice.service.LoanService;
import ru.itis.pashin.websiteservice.service.UserService;

import java.util.List;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Controller
@RequestMapping("/website/loan")
@RequiredArgsConstructor
public class LoanApplicationController {

    private static final String ERROR_PAGE_TITLE = "error_page";
    private static final String ERROR_PARAM_TITLE = "error";
    private final UserService userService;
    private final LoanService loanService;
    private final CatalogsService catalogsService;

    @GetMapping("")
    public String loanPage(Authentication authentication, Model model) {
        UserDTO currentUser = userService.getCurrentUser(authentication);
        List<LoanApplicationDTO> loanList = loanService.getLoans(currentUser);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("loans", loanList);
        return "loans";
    }

    @GetMapping("/create")
    public String getCreateLoanPage(Model model) {
        model.addAttribute("companySizeTypes", catalogsService.findAllCompanySizeType());
        model.addAttribute("industries", catalogsService.findAllIndustry());
        return "create_loan";
    }

    @PostMapping("/create")
    public String createLoan(CreateLoanApplicationDTO createLoanApplicationDTO, Authentication authentication, Model model) {
        try {
            loanService.createLoan(createLoanApplicationDTO, userService.getCurrentUser(authentication));
            return "redirect:/website/loan";
        } catch (ServiceException e) {
            model.addAttribute(ERROR_PARAM_TITLE, e.getServiceErrorCode());
            return ERROR_PAGE_TITLE;
        }
    }

}

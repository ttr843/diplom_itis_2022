package ru.itis.pashin.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.pashin.website.common.model.exception.exception.ServiceException;
import ru.itis.pashin.websiteservice.model.dto.SignUpDTO;
import ru.itis.pashin.websiteservice.service.AuthService;

import java.util.Objects;


@Controller
@RequestMapping("/website/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signIn")
    public String getSignIn(Authentication authentication) {
        if (Objects.nonNull(authentication)) {
            return "redirect:/website/main";
        } else {
            return "sign_in";
        }
    }

    @GetMapping("/signUp")
    public String signUp(Authentication authentication, Model model) {
        if (Objects.nonNull(authentication)) {
            return "redirect:/website/main";
        } else {
            model.addAttribute("signUpDTO", null);
            return "sign_up";
        }
    }

    @PostMapping("/signUp")
    public String signUp(SignUpDTO signUpDTO, Model model) {
        try {
            authService.signUp(signUpDTO);
            return "pre_confirmation";
        } catch (ServiceException e) {
            model.addAttribute("error", e.getServiceErrorCode());
            return "error_page";
        }
    }

}
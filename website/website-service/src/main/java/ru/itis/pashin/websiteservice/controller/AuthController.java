package ru.itis.pashin.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.pashin.websiteservice.model.dto.SignUpDTO;
import ru.itis.pashin.websiteservice.service.AuthService;

import java.util.Objects;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
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
            return "signIn";
        }
    }

    @GetMapping("/signUp")
    public String signUp(Authentication authentication, Model model) {
        if (Objects.nonNull(authentication)) {
            return "redirect:/website/main";
        } else {
            model.addAttribute("signUpDTO", null);
            return "signUp";
        }
    }

    @PostMapping("/signUp")
    public String signUp(SignUpDTO signUpDTO, Model model) {
        try {
            authService.signUp(signUpDTO);
            return "preConfirmation";
        } catch (Exception e) {
            model.addAttribute("errorSignUp", e.getMessage());
            return "signUp";
        }
    }

}
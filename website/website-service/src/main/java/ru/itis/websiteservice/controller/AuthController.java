package ru.itis.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.websiteservice.service.AuthService;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Controller
@RequestMapping("/website/auth")
@RequiredArgsConstructor
public class AuthController {


    private AuthService authService;

    @GetMapping("/signIn")
    public String getSignIn(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/main";
        } else {
            return "signIn";
        }
    }

    @GetMapping("/signUp")
    public String signUp(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/main";
        } else {
            model.addAttribute("SignUpDto", null);
            return "signUp";
        }
    }

    @PostMapping("/signUp")
    public String signUp(Object form) {
        authService.signUp(form);
        return "preConfirmation";
    }
}

package ru.itis.pashin.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;


/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Controller
@RequestMapping(("/website"))
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/main")
    public String mainPage(Authentication authentication) {
        if (Objects.nonNull(authentication)) {
            return "main";
        } else {
            return "redirect:/";
        }
    }
}

package ru.itis.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class RootController {

    private static final String REDIRECT = "redirect:";
    private static final String WELCOME_PAGE = "/welcomePage";

    @GetMapping
    public String rootControl() {
        return REDIRECT + WELCOME_PAGE;
    }

}

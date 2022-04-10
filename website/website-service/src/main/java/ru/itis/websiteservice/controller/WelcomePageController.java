package ru.itis.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Controller
@RequestMapping("/website/welcomePage")
@RequiredArgsConstructor
public class WelcomePageController {

    private static final String WELCOME_PAGE_TITLE = "welcomePage";

    @GetMapping()
    public String welcomePage() {
        return WELCOME_PAGE_TITLE;
    }

}

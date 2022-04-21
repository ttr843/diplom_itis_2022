package ru.itis.pashin.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.pashin.websiteservice.service.NewsService;
import ru.itis.pashin.websiteservice.service.UserService;


@Controller
@RequestMapping(("/website"))
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final NewsService newsService;

    @GetMapping("/main")
    public String mainPage(Authentication authentication, Model model) {
        model.addAttribute("newsList", newsService.getAllNotDeletedNews());
        model.addAttribute("currentUser", userService.getCurrentUser(authentication));
        return "main";

    }
}

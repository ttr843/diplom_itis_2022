package ru.itis.pashin.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class RootController {

    @GetMapping
    public String rootControl() {
        return "redirect:/website/welcomePage";
    }

}

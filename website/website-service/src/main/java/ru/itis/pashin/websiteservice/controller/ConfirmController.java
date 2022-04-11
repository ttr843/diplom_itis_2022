package ru.itis.pashin.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.pashin.websiteservice.service.ConfirmService;

import java.util.UUID;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Controller
@RequestMapping("/website/confirm")
@RequiredArgsConstructor
public class ConfirmController {

    private final ConfirmService confirmService;

    @GetMapping("/{guid}")
    public String confirm(@PathVariable("guid") UUID guid,
                          Model model) {
        boolean isConfirmed = confirmService.confirm(guid);
        model.addAttribute("isConfirmed", isConfirmed);
        return "confirmPage";
    }
}

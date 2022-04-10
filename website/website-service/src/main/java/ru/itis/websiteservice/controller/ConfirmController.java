package ru.itis.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.websiteservice.service.ConfirmService;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Controller("/website/confirm")
@RequiredArgsConstructor
public class ConfirmController {

    private ConfirmService confirmService;

    @GetMapping("/{confirm-code}")
    public String confirm(@PathVariable("confirm-code") String confirmCode,
                          Model model) {
        boolean isConfirmed = confirmService.confirm(confirmCode);
        model.addAttribute("isConfirmed", isConfirmed);
        return "confirmPage";
    }
}

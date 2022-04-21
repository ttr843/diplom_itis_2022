package ru.itis.pashin.websiteservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.pashin.website.common.model.exception.exception.ServiceException;
import ru.itis.pashin.website.common.model.system.dto.NewsDTO;
import ru.itis.pashin.websiteservice.model.dto.CreateBankerDTO;
import ru.itis.pashin.websiteservice.service.*;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Controller
@RequestMapping("/website/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final String USERS_PAGE_TITLE = "users_page";
    private static final String NEWS_PAGE_TITLE = "news";
    private static final String CREATE_BANKER_PAGE_TITLE = "create_banker";
    private static final String CREATE_NEWS_PAGE_TITLE = "create_news";
    private static final String ADMIN_PANEL_PAGE_TITLE = "admin_panel";
    private static final String ERROR_PAGE_TITLE = "error_page";
    private static final String ERROR_PARAM_TITLE = "error";
    private static final String REDIRECT_USERS = "redirect:/website/admin/users";
    private static final String REDIRECT_NEWS = "redirect:/website/admin/news";
    private static final String ADMIN_LOANS_PAGE_TITLE = "admin_loans_page";
    private final AdminService adminService;
    private final CatalogsService catalogsService;
    private final UserService userService;
    private final NewsService newsService;
    private final LoanService loanService;

    @GetMapping
    public String getAdminPanel() {
        return ADMIN_PANEL_PAGE_TITLE;
    }

    @GetMapping("/all-loans")
    public String getAllLoansPage(Model model) {
        model.addAttribute("loans", loanService.getAllLoans());
        return ADMIN_LOANS_PAGE_TITLE;
    }

    @GetMapping("/news")
    public String getNewsPage(Model model) {
        model.addAttribute("newsList", newsService.getAllNews());
        return NEWS_PAGE_TITLE;
    }

    @GetMapping("/create-news")
    public String getCreateNewsPage() {
        return CREATE_NEWS_PAGE_TITLE;
    }

    @PostMapping("/create-news")
    public String createNews(NewsDTO newsDTO, Model model) {
        try {
            newsService.createNews(newsDTO);
            return REDIRECT_NEWS;
        } catch (ServiceException e) {
            model.addAttribute(ERROR_PARAM_TITLE, e.getServiceErrorCode());
            return ERROR_PAGE_TITLE;
        }
    }

    @PostMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable("id") Long id, Model model) {
        try {
            newsService.deleteNews(id);
            return REDIRECT_NEWS;
        } catch (ServiceException e) {
            model.addAttribute(ERROR_PARAM_TITLE, e.getServiceErrorCode());
            return ERROR_PAGE_TITLE;
        }
    }

    @GetMapping("/create-banker")
    public String getCreateBankerPage(Model model) {
        model.addAttribute("banks", catalogsService.findAllBanks());
        return CREATE_BANKER_PAGE_TITLE;
    }

    @PostMapping("/create-banker")
    public String createBanker(CreateBankerDTO createBankerDTO, Model model) {
        try {
            adminService.createBanker(createBankerDTO);
            return REDIRECT_USERS;
        } catch (ServiceException e) {
            model.addAttribute(ERROR_PARAM_TITLE, e.getServiceErrorCode());
            return ERROR_PAGE_TITLE;
        }
    }

    @GetMapping("/users")
    public String getAllUsersPage(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return USERS_PAGE_TITLE;
    }

    @PostMapping("/users/block/{id}")
    public String blockUser(@PathVariable("id") Long id, Model model) {
        try {
            userService.blockUser(id);
            return REDIRECT_USERS;
        } catch (ServiceException e) {
            model.addAttribute(ERROR_PARAM_TITLE, e.getServiceErrorCode());
            return ERROR_PAGE_TITLE;
        }
    }

    @PostMapping("/users/unblock/{id}")
    public String unblockUser(@PathVariable("id") Long id, Model model) {
        try {
            userService.unblockUser(id);
            return REDIRECT_USERS;
        } catch (ServiceException e) {
            model.addAttribute(ERROR_PARAM_TITLE, e.getServiceErrorCode());
            return ERROR_PAGE_TITLE;
        }
    }
}

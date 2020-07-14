package jm.springboot.rest.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/admin")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("userInfo", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return "readUser";
    }

    @GetMapping("/user")
    public String infoUserPage(ModelMap model) {
        model.addAttribute("userInfo", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return "infoUser";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }
}
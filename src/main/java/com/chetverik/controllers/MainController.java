package com.chetverik.controllers;

import com.chetverik.domain.user.Role;
import com.chetverik.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String start() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String greeting(@AuthenticationPrincipal User currentUser, Model model) {
        for (Role role : currentUser.getRoles()) {
            if (role == Role.SUPERUSER) {
                model.addAttribute("admin", true);
            }
        }
        if (currentUser != null) {
            model.addAttribute("username", currentUser.getUsername());
        }
        return "main";
    }

}

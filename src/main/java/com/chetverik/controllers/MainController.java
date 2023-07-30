package com.chetverik.controllers;

import com.chetverik.components.IAuthenticationFacade;
import com.chetverik.domain.user.Role;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    @Value("${upload.path}")
    private String uploadPath;

    private IAuthenticationFacade authenticatedUser;
    private UserRepo userRepo;

    public MainController(IAuthenticationFacade authenticatedUser, UserRepo userRepo) {
        this.authenticatedUser = authenticatedUser;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String start() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String greeting(Model model) {
        String currentUserName = authenticatedUser.getAuthentication().getName();
        User byUsername = userRepo.findByUsername(currentUserName);
        for (Role role : byUsername.getRoles()) {
            if (role == Role.ADMIN) {
                model.addAttribute("admin", true);
            }
        }
        if (currentUserName != null && !currentUserName.isEmpty()) {
            model.addAttribute("username", currentUserName);
        }
        return "main";
    }

}

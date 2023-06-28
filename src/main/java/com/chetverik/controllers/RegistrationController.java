package com.chetverik.controllers;

import com.chetverik.domain.Role;
import com.chetverik.domain.User;
import com.chetverik.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        User user = new User(username, password);
        User userFromDb = userRepo.findByUsername(username);
        if (userFromDb != null && !userFromDb.getUsername().isEmpty()) {
            model.addAttribute("message", "user is exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String logInView(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String logInViewTest() {
        return "login";
    }
    @PostMapping("/del")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        User user = new User(username, password);
        User byUsername = userRepo.findByUsername(username);
        if (byUsername != null) {
            userRepo.delete(byUsername);
            model.addAttribute("message", "user was delete");
            return "redirect:/login";
        }
        return "registration";
    }

}

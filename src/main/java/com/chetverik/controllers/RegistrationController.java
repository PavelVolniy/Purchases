package com.chetverik.controllers;

import com.chetverik.domain.user.Role;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.BranchRepo;
import com.chetverik.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private UserRepo userRepo;
    private BranchRepo branchRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("branches", branchRepo.findAll());
        model.addAttribute("roleList", Role.values());
        return "registration";
    }

    @PostMapping("/registration")
    @PreAuthorize("hasAuthority('SUPERUSER')")
    public String addUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String branch,
            Model model
    ) {
        User user = new User();
        User userFromDb = userRepo.findByUsername(username);
        if (userFromDb != null && !userFromDb.getUsername().isEmpty()) {
            model.addAttribute("message", "user is exists!");
            return "registration";
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setBranch(branchRepo.findByName(branch));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.MANAGER));
        userRepo.save(user);
        return "redirect:/settings";
    }

    @GetMapping("/login")
    public String logInView(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String logInViewTest(@RequestParam String username, @RequestParam String password, Model model) {
        User byUsername = userRepo.findByUsername(username);
        if (byUsername != null && !username.isEmpty()) {
            byUsername.setActive(true);
        }
        userRepo.save(byUsername);
        model.addAttribute("username", byUsername.getUsername());
        return "redirect:/main";
    }

    @PostMapping("/del")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        User user = new User();
        User byUsername = userRepo.findByUsername(username);
        if (byUsername != null) {
            userRepo.delete(byUsername);
            model.addAttribute("message", "user was delete");
            return "redirect:/login";
        }
        return "registration";
    }

    @PostMapping("/logout")
    public String logOut(@RequestParam User user) {
        user.setActive(false);
        System.out.println(user);
        userRepo.save(user);
        return "replace:/login";
    }


}

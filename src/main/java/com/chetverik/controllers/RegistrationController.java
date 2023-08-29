package com.chetverik.controllers;

import com.chetverik.domain.entityes.Branch;
import com.chetverik.domain.user.Role;
import com.chetverik.domain.user.User;
import com.chetverik.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private UserService userService;

    @GetMapping("/registration")
    @PreAuthorize("hasAuthority('SUPERUSER')")
    public String registration(Model model) {
        model.addAttribute("branches", userService.findAllBranches());
        model.addAttribute("roleList", Role.values());
        return "registration";
    }

    @PostMapping("/registration")
    @PreAuthorize("hasAuthority('SUPERUSER')")
    public String addUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String branch,
            @RequestParam Map<String, String> form,
            Model model
    ) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        User user = new User();
        User userFromDb = userService.findUserByUsername(username);
        if (userFromDb != null && !userFromDb.getUsername().isEmpty()) {
            model.addAttribute("message", "user is exists!");
            return "registration";
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setBranch(userService.findBranchByName(branch));
        user.setActive(true);
        Set<Role> userRoles = new TreeSet<>();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                userRoles.add(Role.valueOf(key));
            }
        }
        user.setRoles(userRoles);
        userService.saveUser(user);
        return "redirect:/settings";
    }

    @GetMapping("/login")
    public String logInView(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String logInViewTest(@RequestParam String username,
                                @RequestParam String password,
                                Model model) {
        User byUsername = userService.findUserByUsername(username);
        if (byUsername != null && !username.isEmpty()) {
            byUsername.setActive(true);
        }
        userService.saveUser(byUsername);
        model.addAttribute("username", byUsername.getUsername());
        return "redirect:/main";
    }

    @PostMapping("/logout")
    public String logOut(@RequestParam User user) {
        user.setActive(false);
        userService.saveUser(user);
        return "replace:/login";
    }

    @GetMapping("/reg")
    public String regSuperUser() {
        userService.saveBranch(new Branch("MANAGER"));
        userService.saveBranch(new Branch("ADMIN"));
        User user = new User("q", "q", Collections.singleton(Role.ADMIN), (Branch) userService.findAllBranches(), true);
        userService.saveUser(user);
        return "replace:/login";
    }

}

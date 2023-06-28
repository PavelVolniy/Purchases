package com.chetverik.controllers;

import com.chetverik.domain.Role;
import com.chetverik.domain.User;
import com.chetverik.repositories.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("userList", userRepo.findAll());
        return "userList";
    }

    @GetMapping("/{id}")
    public String userEditForm(@PathVariable String id, Model model) {
        Long userId = Long.valueOf(id);
        Iterable<User> user = userRepo.findAllById(Collections.singleton(userId));
        model.addAttribute("user", user);
        model.addAttribute("roleList", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String saveUser(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }

    @PostMapping("/del")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(
            @RequestParam String username,
            Model model
    ) {
        User byUsername = userRepo.findByUsername(username);
        return "redirect:/user";
    }

}

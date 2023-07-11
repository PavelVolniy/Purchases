package com.chetverik.controllers;

import com.chetverik.domain.user.Role;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.BranchRepo;
import com.chetverik.repositories.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private UserRepo userRepo;
    private BranchRepo branchRepo;

    public UserController(UserRepo userRepo, BranchRepo branchRepo) {
        this.userRepo = userRepo;
        this.branchRepo = branchRepo;
    }

    @GetMapping
    public String getUserKList(Model model){
        model.addAttribute("userList", userRepo.findAll());
        return "userList";
    }

    @GetMapping("/{id}")
    public String userEditForm(@PathVariable String id, Model model) {
        Long userId = Long.valueOf(id);
        Iterable<User> user = userRepo.findAllById(Collections.singleton(userId));
        model.addAttribute("user", user);
        model.addAttribute("roleList", Role.values());
        model.addAttribute("branches", branchRepo.findAll());
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

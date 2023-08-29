package com.chetverik.service;

import com.chetverik.domain.entityes.Branch;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.BranchRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.chetverik.repositories.UserRepo;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepo userRepo;
    private BranchRepo branchRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public Iterable<User> getUserList(){
        return userRepo.findAll();
    }

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public Branch findBranchByName(String branch) {
        return branchRepo.findByName(branch);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void saveBranch(Branch branch) {
        branchRepo.save(branch);
    }

    public Iterable<Branch> findAllBranches() {
        return branchRepo.findAll();
    }
}

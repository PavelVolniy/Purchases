package com.chetverik.kotlin.controller

import com.chetverik.domain.contract.Branch
import com.chetverik.repositories.BranchRepo
import com.chetverik.repositories.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
open class SettingsController (private val userRepo: UserRepo, private val branchRepo: BranchRepo) {


    @GetMapping("/settings")
   open fun getSettings(model: Model): String {
        println(userRepo.findAll())
        model.addAttribute("userList", userRepo.findAll())
        model.addAttribute("branches", branchRepo.findAll())
        return "settings"
    }

    @PostMapping("/settings")
    open fun addNewBranch(@RequestParam newBranch: String, model: Model): String {
        if (newBranch!=null && newBranch.isNotEmpty()){
            println(newBranch)
            val findByNameBranch = branchRepo.findByName(newBranch)
            if (findByNameBranch!=null){
                model.addAttribute("errorMessage", "branch is exist")
            } else{
                branchRepo.save(Branch(newBranch))
            }
        }
        return "redirect:/settings"
    }


}
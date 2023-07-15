package com.chetverik.kotlin.controller

import com.chetverik.domain.contract.Branch
import com.chetverik.domain.contract.TypeOfPurchase
import com.chetverik.repositories.BranchRepo
import com.chetverik.repositories.TypePurchaseRepo
import com.chetverik.repositories.UserRepo
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@PreAuthorize("hasAuthority('SUPERUSER')")
open class SettingsController(
    private val userRepo: UserRepo,
    private val branchRepo: BranchRepo,
    private val typeOfPurchaseRepo: TypePurchaseRepo,
) {


    @GetMapping("/settings")
    open fun getSettings(model: Model): String {
        println(userRepo.findAll())
        model.addAttribute("userList", userRepo.findAll())
        model.addAttribute("branches", branchRepo.findAll())
        model.addAttribute("types", typeOfPurchaseRepo.findAll())
        return "settings"
    }

    @PostMapping("/settings")
    open fun addNewBranch(@RequestParam newBranch: String, model: Model): String {
        if (newBranch != null && newBranch.isNotEmpty()) {
            val findByNameBranch = branchRepo.findByName(newBranch)
            if (findByNameBranch != null) {
                model.addAttribute("errorMessage", "branch is exist")
            } else {
                branchRepo.save(Branch(newBranch))
            }
        }
        return "redirect:/settings"
    }

    @GetMapping("/branch/{id}")
    open fun editBranch(@PathVariable id: String, model: Model): String {
        val branchId = id.toLong()
        val branchById = branchRepo.findAllById(Collections.singleton(branchId))
        model.addAttribute("branchById", branchById)
        return "branchEdit"
    }

    @PostMapping("/branchEdit")
    open fun saveBranchEdited(
        @RequestParam branchName: String,
        @RequestParam("branchId") branch: Branch,
    ): String {
        if (branchName != null && branchName.isNotEmpty()) {
            branch.name = branchName
            branchRepo.save(branch)
        }
        return "redirect:/settings"
    }

    @PostMapping("/settings/type")
    open fun addNewTypeOfPurchase(@RequestParam newTypeOfPurchase: String): String {
        typeOfPurchaseRepo.save(TypeOfPurchase(newTypeOfPurchase))

        return "redirect:/settings"
    }

    @GetMapping("/type/{id}")
    open fun editTypePurchase(@PathVariable id: String, model: Model): String {
        val typePurchaseId = id.toLong()
        val typeOfPurchase = typeOfPurchaseRepo.findAllById(Collections.singleton(typePurchaseId))
        model.addAttribute("typePurchase", typeOfPurchase)
        return "/editType"
    }

    @PostMapping("/editType")
    open fun saveTypePurchase(
        @RequestParam namePurchase: String,
        @RequestParam("typeId") type: TypeOfPurchase
    ): String {
        if (namePurchase!=null && namePurchase.isNotEmpty()){
            type.nameTypeOfPurchase = namePurchase
        }
        typeOfPurchaseRepo.save(type)
        return "redirect:/settings"
    }
}
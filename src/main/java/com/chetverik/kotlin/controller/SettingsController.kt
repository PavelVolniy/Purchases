package com.chetverik.kotlin.controller

import com.chetverik.domain.entityes.Branch
import com.chetverik.domain.entityes.TypeCompany
import com.chetverik.domain.entityes.TypeOfPurchase
import com.chetverik.repositories.BranchRepo
import com.chetverik.repositories.TypeCompanyRepo
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
    private val typeCompanyRepo: TypeCompanyRepo,
) {


    @GetMapping("/settings")
    open fun getSettings(model: Model): String {
        println(userRepo.findAll())
        model.addAttribute("userList", userRepo.findAll())
        model.addAttribute("branches", branchRepo.findAll())
        model.addAttribute("typesPurchase", typeOfPurchaseRepo.findAll())
        model.addAttribute("typesCompany", typeCompanyRepo.findAll())
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

    @GetMapping("/branch/del/{id}")
    open fun delBranch(@PathVariable id: String): String {
        if (id.isNotEmpty()) {
            val branchId = id.toLong()
            branchRepo.deleteById(branchId)
        }
        return "redirect:/settings"
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

    @PostMapping("/settings/typesPurchase")
    open fun addNewTypeOfPurchase(@RequestParam newTypeOfPurchase: String): String {
        typeOfPurchaseRepo.save(
            TypeOfPurchase(
                newTypeOfPurchase
            )
        )

        return "redirect:/settings"
    }

    @PostMapping("settings/typeCompany")
    open fun addNewTypOfCompany(@RequestParam newTypeCompany: String): String {
        if (newTypeCompany.isNotEmpty()) {
            typeCompanyRepo.save(TypeCompany(newTypeCompany))
        }
        return "redirect:/settings"
    }

    @GetMapping("/typeCompany/{id}")
    open fun editTypeCompanyForm(@PathVariable id: String, model: Model): String {
        model.addAttribute("typeOfCompany", typeCompanyRepo.findAllById(Collections.singleton(id.toLong())))
        return "/editTypeCompany"
    }

    @GetMapping("/typeCompany/del/{id}")
    open fun delTypeCompany(@PathVariable id: String): String{
        if (id.isNotEmpty()){
            typeCompanyRepo.deleteById(id.toLong())
        }
        return "redirect:/settings"
    }

    @PostMapping("/editTypeCompany")
    open fun saveTypeCompany(
        @RequestParam newNameTypeCompany: String,
        @RequestParam("typeCompanyId") typeCompany: TypeCompany,
    ): String {
        if (newNameTypeCompany.isNotEmpty()){
            typeCompany.nameTypeCompany = newNameTypeCompany
            typeCompanyRepo.save(typeCompany)
        }
        return "redirect:/settings"
    }

    @GetMapping("/typePurchase/{id}")
    open fun editTypePurchase(@PathVariable id: String, model: Model): String {
        if (id.isNotEmpty()) {
            val typePurchaseId = id.toLong()
            val typeOfPurchase = typeOfPurchaseRepo.findAllById(Collections.singleton(typePurchaseId))
            model.addAttribute("typePurchase", typeOfPurchase)
        }
        return "/editType"
    }

    @GetMapping("/typePurchase/del/{id}")
    open fun delTypePurchase(@PathVariable id: String): String {
        if (id.isNotEmpty()) {
            val purchaseId = id.toLong()
            typeOfPurchaseRepo.deleteById(purchaseId)
        }
        return "redirect:/settings"
    }

    @PostMapping("/editType")
    open fun saveTypePurchase(
        @RequestParam namePurchase: String,
        @RequestParam("typeId") type: TypeOfPurchase,
    ): String {
        if (namePurchase != null && namePurchase.isNotEmpty()) {
            type.nameTypeOfPurchase = namePurchase
        }
        typeOfPurchaseRepo.save(type)
        return "redirect:/settings"
    }
}
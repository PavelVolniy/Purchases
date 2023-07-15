package com.chetverik.kotlin.controller

import com.chetverik.components.IAuthenticationFacade
import com.chetverik.domain.contract.TypeOfPurchase
import com.chetverik.domain.purchase.Purchase
import com.chetverik.domain.purchase.PurchaseFieldNames
import com.chetverik.domain.user.User
import com.chetverik.repositories.PurchaseRepo
import com.chetverik.repositories.TypePurchaseRepo
import com.chetverik.repositories.UserRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
open class PurchaseController(
    private val purchaseRepo: PurchaseRepo,
    private val userRepo: UserRepo,
    private val authenticationFacade: IAuthenticationFacade,
    private val typeOfPurchaseRepo: TypePurchaseRepo,
) {

    @GetMapping("/purchase")
    open fun getPurchaseList(model: Model): String {
        model.addAttribute("names", PurchaseFieldNames.getContractNames())
        model.addAttribute("purchases", purchaseRepo.findAll())
        return "purchase"
    }

    @GetMapping("/purchase/{id}")
    open fun editPurchase(@PathVariable id: String, model: Model): String {
        val purchaseId: Long = id.toLong()
        val findAllById = purchaseRepo.findById(purchaseId)
        return if (findAllById.get().branch == getCurrentUser().branch){
            "purchaseEdit"
        } else{
            "redirect:/purchase"
        }
    }

    @GetMapping("/addPurchase")
    open fun addPurchaseForm(model: Model) :String{
        model.addAttribute("branch", getCurrentUser().branch)
        model.addAttribute("types", typeOfPurchaseRepo.findAll())
        return "addPurchase"
    }

    @PostMapping("/addPurchase")
    open fun addPurchase(
//        @RequestParam branch: String,
        @RequestParam namePurchase: String,
        @RequestParam("typeOfPurchase") typeOfPurchase: TypeOfPurchase,
        @RequestParam(required = false) conditionOfPurchase: Boolean,
        @RequestParam dateOfPlacement: String,
        @RequestParam dateOfEnd: String,
        @RequestParam dateOfReview: String,
        @RequestParam numberOfContract: String,
        @RequestParam startPrice: Double,
        @RequestParam(defaultValue = "0") applicationSubmitted: Int,
        @RequestParam(defaultValue = "0") applicationAdmitted: Int,
        @RequestParam(defaultValue = "0.0") priceApplicationOne: Double,
        @RequestParam(defaultValue = "0.0") priceApplicationTwo: Double,
        @RequestParam(defaultValue = "0.0") differenceValues: Double,
        @RequestParam(defaultValue = "0.0") priceOfContract: Double,
        @RequestParam(defaultValue = "0.0") economy: Double,
        @RequestParam(defaultValue = "0") numberOfProcedureOnEIS: Int,
        model: Model,
    ): String {
        val newPurchase = Purchase(
            getCurrentUser().branch,
            namePurchase,
            typeOfPurchase,
            conditionOfPurchase,
            dateOfPlacement,
            dateOfEnd,
            dateOfReview,
            numberOfContract,
            startPrice,
            applicationSubmitted,
            applicationAdmitted,
            priceApplicationOne,
            priceApplicationTwo,
            differenceValues,
            priceOfContract,
            economy,
            numberOfProcedureOnEIS
        )
        val byName = purchaseRepo.findByNamePurchase(newPurchase.namePurchase)
        if (byName != null) {
            model.addAttribute("errorMessage", "purchase is exist")
        } else {
            purchaseRepo.save(newPurchase)
        }
        return "redirect:/purchase"
    }

    private fun getCurrentUser(): User {
        val userName = authenticationFacade.authentication.name
        return userRepo.findByUsername(userName)
    }
}
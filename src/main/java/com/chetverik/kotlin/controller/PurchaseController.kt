package com.chetverik.kotlin.controller

import com.chetverik.components.IAuthenticationFacade
import com.chetverik.domain.entityes.TypeOfPurchase
import com.chetverik.domain.purchase.Purchase
import com.chetverik.domain.purchase.PurchaseFieldNames
import com.chetverik.domain.user.Role
import com.chetverik.domain.user.User
import com.chetverik.repositories.PurchaseRepo
import com.chetverik.repositories.TypePurchaseRepo
import com.chetverik.repositories.UserRepo
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.math.abs

@Controller
@RequestMapping("/purchase")
open class PurchaseController(
    private val purchaseRepo: PurchaseRepo,
    private val userRepo: UserRepo,
    private val authenticationFacade: IAuthenticationFacade,
    private val typeOfPurchaseRepo: TypePurchaseRepo,
) {

    @GetMapping()
    open fun getPurchaseList(model: Model): String {
        model.addAttribute("names", PurchaseFieldNames.getContractNames())
        model.addAttribute("purchases", purchaseRepo.findAll())
        return "purchase"
    }

    @GetMapping("/editPurchase/{id}")
    open fun editPurchaseForm(@PathVariable id: String, model: Model, @AuthenticationPrincipal currentUser: User): String {
        val purchaseId: Long = id.toLong()
        val findById = purchaseRepo.findAllById(Collections.singleton(purchaseId))
        if (findById.first().branch == currentUser.branch || currentUser.roles.contains(Role.ADMIN)) {
            model.addAttribute("purchase", findById)
            model.addAttribute("types", typeOfPurchaseRepo.findAll())
            return "editPurchase"
        } else {
            return "redirect:/purchase"
        }
    }

    @GetMapping("/editPurchase/del/{id}")
    open fun delPurchase(@PathVariable id: String): String {
        if (id.isNotEmpty()) {
            purchaseRepo.deleteById(id.toLong())
        }
        return "redirect:/purchase"
    }

    @PostMapping("editPurchase")
    open fun editPurchase(
        @RequestParam("purchaseId") purchase: Purchase,
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
    ): String {
        if (namePurchase.isNotEmpty()) {
            purchase.namePurchase = namePurchase
            purchase.typeOfPurchase = typeOfPurchase
            if (conditionOfPurchase == null) {
                purchase.isConditionOfPurchase = false
            } else {
                purchase.isConditionOfPurchase = conditionOfPurchase
            }
            purchase.dateOfPlacement = dateOfPlacement
            purchase.dateOfEnd = dateOfEnd
            purchase.dateOfReview = dateOfReview
            purchase.numberOfContract = numberOfContract
            purchase.startPrice = startPrice
            purchase.applicationSubmitted = applicationSubmitted
            purchase.applicationAdmitted = applicationAdmitted
            purchase.priceApplicationOne = priceApplicationOne
            purchase.priceApplicationTwo = priceApplicationTwo
            purchase.differenceValues = abs(priceApplicationOne - priceApplicationTwo)
            purchase.priceOfContract = priceOfContract
            purchase.economy = startPrice-priceOfContract
            purchase.numberOfProcedureOnEIS = numberOfProcedureOnEIS
        }
        purchaseRepo.save(purchase)
        return "redirect:/purchase"
    }

    @GetMapping("/addPurchase")
    open fun addPurchaseForm(model: Model, @AuthenticationPrincipal currentUser: User): String {
        model.addAttribute("branch", currentUser.branch)
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
        @AuthenticationPrincipal currentUser: User,
        model: Model,
    ): String {
        val newPurchase = Purchase(
            currentUser.branch,
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
            abs(priceApplicationOne-priceApplicationTwo),
            priceOfContract,
            economy,
            numberOfProcedureOnEIS,
            userRepo.findByUsername(currentUser.username)
        )
        purchaseRepo.save(newPurchase)
        return "redirect:/purchase"
    }

    private fun getCurrentUser(): User {
        val userName = authenticationFacade.authentication.name
        return userRepo.findByUsername(userName)
    }
}
package com.chetverik.kotlin.controller

import com.chetverik.domain.purchase.Purchase
import com.chetverik.domain.purchase.PurchaseFieldNames
import com.chetverik.repositories.PurchaseRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
open class PurchaseController(private val purchaseRepo: PurchaseRepo) {

    @GetMapping("/purchase")
    open fun getPurchaseList(model: Model): String {
        model.addAttribute("names", PurchaseFieldNames.getContractNames())
        model.addAttribute("purchases", purchaseRepo.findAll())
        return "purchase"
    }

    @GetMapping("/purchase/{id}")
    open fun editPurchase(@PathVariable id: String, model: Model): String {
        val purchaseId: Long = id.toLong()
        val findAllById = purchaseRepo.findAllById(Collections.singleton(purchaseId))

        return "purchaseEdit"
    }

    @GetMapping("/addPurchase")
    open fun addPurchaseForm() = "addPurchase"

    @PostMapping("/addPurchase")
    open fun addPurchase(
        @RequestParam branch: String,
        @RequestParam namePurchase: String,
        @RequestParam typeOfPurchase: String,
        @RequestParam conditionOfPurchase: Boolean,
        @RequestParam dateOfPlacement: String,
        @RequestParam dateOfEnd: String,
        @RequestParam dateOfReview: String,
        @RequestParam numberOfContract: String,
        @RequestParam startPrice: Double,
        @RequestParam applicationSubmitted: String,
        @RequestParam applicationAdmitted: String,
        @RequestParam priceApplicationOne: Double,
        @RequestParam priceApplicationTwo: Double,
        @RequestParam differenceValues: Double,
        @RequestParam priceOfContract: Double,
        @RequestParam economy: Double,
        @RequestParam numberOfProcedureOnEIS: String,
        model: Model,
    ): String {
        val newPurchase = Purchase(
            branch,
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
}
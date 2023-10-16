package com.chetverik.kotlin.controller

import com.chetverik.domain.entityes.Branch
import com.chetverik.domain.entityes.TypeCompany
import com.chetverik.domain.entityes.TypeOfPurchase
import com.chetverik.repositories.*
import com.chetverik.service.ExcelService
import com.chetverik.service.SettingsService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletResponse

@Controller
@PreAuthorize("hasAuthority('SUPERUSER')")
open class SettingsController(
    private val excelService: ExcelService,
    private val settingsService: SettingsService,
) {


    @GetMapping("/settings")
    open fun getSettings(model: Model): String {
        model.addAttribute("userList", settingsService.userList)
        model.addAttribute("branches", settingsService.branchList)
        model.addAttribute("typesPurchase", settingsService.typePurchaseList)
        model.addAttribute("typesCompany", settingsService.typeCompanyList)
        model.addAttribute("suppliers", settingsService.supplierList)
        model.addAttribute("currentDate", SimpleDateFormat("dd.MM.yyyy").format(Date()))
        return "settings"
    }

    @PostMapping("/settings")
    open fun addNewBranch(@RequestParam newBranch: String, model: Model): String {
        if (newBranch.isNotEmpty()) {
            val findByNameBranch = settingsService.findBranchByName(newBranch)
            if (findByNameBranch != null) {
                model.addAttribute("errorMessage", "branch is exist")
            } else {
                settingsService.saveBranch(Branch(newBranch))
            }
        }
        return "redirect:/settings"
    }

    @GetMapping("/branch/{id}")
    open fun editBranch(@PathVariable id: String, model: Model): String {
        val branchId = id.toLong()
        val branchById = settingsService.findAllBranchById(branchId)
        model.addAttribute("branchById", branchById)
        return "branchEdit"
    }

    @GetMapping("/branch/del/{id}")
    open fun delBranch(@PathVariable id: String): String {
        if (id.isNotEmpty()) {
            val branchId = id.toLong()
            settingsService.deleteBranchByID(branchId)
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
            settingsService.saveBranch(branch)
        }
        return "redirect:/settings"
    }

    @PostMapping("/settings/typesPurchase")
    open fun addNewTypeOfPurchase(@RequestParam newTypeOfPurchase: String): String {
        settingsService.saveTypeOfPurchase(
            TypeOfPurchase(newTypeOfPurchase)
        )

        return "redirect:/settings"
    }

    @PostMapping("settings/typeCompany")
    open fun addNewTypOfCompany(@RequestParam newTypeCompany: String): String {
        if (newTypeCompany.isNotEmpty()) {
            settingsService.saveTypeCompany(TypeCompany(newTypeCompany))
        }
        return "redirect:/settings"
    }

    @GetMapping("/typeCompany/{id}")
    open fun editTypeCompanyForm(@PathVariable id: String, model: Model): String {
        model.addAttribute("typeOfCompany", settingsService.findAllTypeCompanyById(id.toLong()))
        return "/editTypeCompany"
    }

    @GetMapping("/typeCompany/del/{id}")
    open fun delTypeCompany(@PathVariable id: String): String {
        if (id.isNotEmpty()) {
            settingsService.deleteTypCompanyById(id.toLong())
        }
        return "redirect:/settings"
    }

    @PostMapping("/editTypeCompany")
    open fun saveTypeCompany(
        @RequestParam newNameTypeCompany: String,
        @RequestParam("typeCompanyId") typeCompany: TypeCompany,
    ): String {
        if (newNameTypeCompany.isNotEmpty()) {
            typeCompany.nameTypeCompany = newNameTypeCompany
            settingsService.saveTypeCompany(typeCompany)
        }
        return "redirect:/settings"
    }

    @GetMapping("/typePurchase/{id}")
    open fun editTypePurchase(@PathVariable id: String, model: Model): String {
        if (id.isNotEmpty()) {
            val typePurchaseId = id.toLong()
            val typeOfPurchase = settingsService.findAllTypePurchaseBiId(typePurchaseId)
            model.addAttribute("typePurchase", typeOfPurchase)
        }
        return "/editType"
    }

    @GetMapping("/typePurchase/del/{id}")
    open fun delTypePurchase(@PathVariable id: String): String {
        if (id.isNotEmpty()) {
            val purchaseId = id.toLong()
            settingsService.deleteTypePurchaseById(purchaseId)
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
        settingsService.saveTypeOfPurchase(type)
        return "redirect:/settings"
    }

    @GetMapping("/exportTables")
    open fun exportTablesPurchase(response: HttpServletResponse) {
        response.contentType = "application/octet-stream"
        val headerKey = "Content-Disposition"
        val headerValue = "attachment; filename=Contracts_${Date()}.xls"
        response.setHeader(headerKey, headerValue)
        excelService.exportContractsToExcel(response)
        excelService.testImportFromExcel()
    }

    @GetMapping("/supplier/{id}")
    open fun editSupplierForm(
        @PathVariable id: String,
        model: Model,
    ): String {
        model.addAttribute("supplier", settingsService.getSupplierById(id))
        return "editSupplier"
    }

    @PostMapping("/supplier")
    open fun editSupplier(
        @RequestParam(defaultValue = "not") nameSupplier: String,
        @RequestParam(required = true) inn: String
    ): String {
        val supplierByInn = settingsService.getSupplierByInn(inn)
        if (supplierByInn!=null){
            supplierByInn.nameSupplier = nameSupplier
            settingsService.saveSupplier(supplierByInn)
        }
        return "redirect:/settings"
    }

    @GetMapping("/supplier/dell/{id}")
    open fun delSupplier(@PathVariable id: String): String {
        settingsService.dellSupplier(id)
        return "redirect:/settings"
    }

}
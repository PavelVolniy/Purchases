package com.chetverik.controllers;

import com.chetverik.domain.contract.ContractFieldNames;
import com.chetverik.repositories.BranchRepo;
import com.chetverik.repositories.ContractRepo;
import com.chetverik.repositories.TypePurchaseRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/filter")
public class FilterController {
    private ContractRepo contractRepo;
    private BranchRepo branchRepo;
    private TypePurchaseRepo typePurchaseRepo;

    @GetMapping()
    public String filterFormTest(Model model) {
        model.addAttribute("branches", branchRepo.findAll());
        model.addAttribute("types", typePurchaseRepo.findAll());
        return "filter";
    }

    @PostMapping()
    public String getFilterForm(
            @RequestParam(defaultValue = "") String branchID,
            @RequestParam(defaultValue = "") String nameOfContract,
            @RequestParam(defaultValue = "") String typeOfPurchaseId,
            @RequestParam(defaultValue = "") String numberOfContract,
            @RequestParam(defaultValue = "") String sumMore,
            @RequestParam(defaultValue = "") String sumLess,
            @RequestParam(defaultValue = "") String nameOfSupplier,
            @RequestParam(defaultValue = "") String innOfSupplier,
            Model model
    ) {
        model.addAttribute("names", ContractFieldNames.getContractNames());
        model.addAttribute("contracts", contractRepo.findAll());
        return "contracts";
    }
}

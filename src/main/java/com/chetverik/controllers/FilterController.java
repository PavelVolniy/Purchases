package com.chetverik.controllers;

import com.chetverik.domain.contract.ContractFieldNames;
import com.chetverik.domain.entityes.Branch;
import com.chetverik.domain.entityes.TypeOfPurchase;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.BranchRepo;
import com.chetverik.repositories.TypePurchaseRepo;
import com.chetverik.service.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/filter")
public class FilterController {
    private BranchRepo branchRepo;
    private TypePurchaseRepo typePurchaseRepo;
    private ContractService contractService;

    @GetMapping()
    public String filterFormTest(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("branches", branchRepo.findAll());
        model.addAttribute("types", typePurchaseRepo.findAll());
        model.addAttribute("userName", user.getUsername());
        return "filter";
    }

    @GetMapping("/res")
    public String getFilters(
            @RequestParam(required = false) Branch branchID,
            @RequestParam(required = false) String nameOfContract,
            @RequestParam(required = false) TypeOfPurchase typeOfPurchase,
            @RequestParam(required = false) String numberOfContract,
            @RequestParam(required = false) String sumMore,
            @RequestParam(required = false) String sumLess,
            @RequestParam(required = false) String nameOfSupplier,
            @RequestParam(required = false) Long innOfSupplier,
            Model model
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("branch", branchID);
        map.put("nameOfContract", nameOfContract);
        map.put("typeOfPurchase", typeOfPurchase);
        map.put("numberOfContract", numberOfContract);
        map.put("sumMore", sumMore);
        map.put("sumLess", sumLess);
        map.put("nameOfSupplier", nameOfSupplier);
        map.put("innOfSupplier", innOfSupplier);
        model.addAttribute("names", ContractFieldNames.getContractNames());
        model.addAttribute("contracts", contractService.getContractsByParam(map));
        return "contracts";
    }

}

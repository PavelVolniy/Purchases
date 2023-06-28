package com.chetverik.controllers;

import com.chetverik.domain.Contract;
import com.chetverik.repositories.ContractRepository;
import com.chetverik.service.CheckedContractFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContractsController {

    @Autowired
    private ContractRepository contractRepository;

    @GetMapping("/contracts")
    public String getContractsList(Model model) {
        Iterable<Contract> contracts = contractRepository.findAll();
        model.addAttribute("contracts", contracts);
        return "contracts";
    }

    @GetMapping("/addContract")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addContractForm() {
        return "addContract";
    }


    @PostMapping("/addContract")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addContract(
            @RequestParam String contractName,
            @RequestParam String dateTo,
            @RequestParam String dateFrom,
            @RequestParam Double price,
            Model model
    ) {
        if (contractName != null && !contractName.isEmpty() &&
                dateTo != null && dateFrom != null) {
            Contract contract = new Contract(contractName, dateTo, dateFrom, price);
            System.out.println(contract);
            if (CheckedContractFields.check(contract)) {
                contractRepository.save(contract);
            }
        }
        return "redirect:/contracts";
    }

    @GetMapping("/clearContractList")
    public String clearContractList() {
        contractRepository.deleteAll();
        return "redirect:/contracts";
    }
}

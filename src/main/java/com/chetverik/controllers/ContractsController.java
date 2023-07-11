package com.chetverik.controllers;

import com.chetverik.components.IAuthenticationFacade;
import com.chetverik.domain.contract.Contract;
import com.chetverik.domain.contract.ContractFieldNames;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.BranchRepo;
import com.chetverik.repositories.ContractRepository;
import com.chetverik.repositories.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContractsController {
    private ContractRepository contractRepository;
    private BranchRepo branchRepo;
    private IAuthenticationFacade authenticationFacade;
    private UserRepo userRepo;

    public ContractsController(ContractRepository contractRepository,
                               BranchRepo branchRepo,
                               IAuthenticationFacade authenticationFacade,
                               UserRepo userRepo) {
        this.contractRepository = contractRepository;
        this.branchRepo = branchRepo;
        this.authenticationFacade = authenticationFacade;
        this.userRepo = userRepo;
    }

    @GetMapping("/contracts")
    public String getContractsList(Model model) {
        Iterable<Contract> contracts = contractRepository.findAll();
        for (Contract item : contracts) {
            System.out.println(item);
        }
        model.addAttribute("names", ContractFieldNames.getContractNames());
        model.addAttribute("contracts", contracts);

        return "contracts";
    }

    @GetMapping("/addContract")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addContractForm(Model model) {
        model.addAttribute("names", ContractFieldNames.getContractNames());
        model.addAttribute("contractList", ContractFieldNames.getContractNames());
        model.addAttribute("branch", getCurrentUser().getBranch());
        return "addContract";
    }


    @PostMapping("/addContract")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addContract(
//            @RequestParam String branch,
            @RequestParam String nameOfContract,
            @RequestParam String pp_poz_ep,
            @RequestParam String typeOfPurchase,
            @RequestParam String numberOfContract,
            @RequestParam String dateOfContract,
            @RequestParam Double sum,
            @RequestParam String dateOfExecutionContract,
            @RequestParam String nameOfSupplier,
            @RequestParam(defaultValue = "000") int innOfSupplier,
            @RequestParam String typeOfCompany,
            @RequestParam String numberOfRegistryEntry,
            @RequestParam String additionalAgreement,
            @RequestParam String okdp2,
            @RequestParam String f_i_o
    ) {
        Contract newContract;
        newContract = new Contract(
                getCurrentUser().getBranch(),
                nameOfContract,
                pp_poz_ep,
                typeOfPurchase,
                numberOfContract,
                dateOfContract,
                sum,
                dateOfExecutionContract,
                nameOfSupplier,
                innOfSupplier,
                typeOfCompany,
                numberOfRegistryEntry,
                additionalAgreement,
                okdp2,
                f_i_o);
        contractRepository.save(newContract);
        return "redirect:/contracts";
    }

    @GetMapping("/clearContractList")
    public String clearContractList() {
        contractRepository.deleteAll();
        return "redirect:/contracts";
    }

    private User getCurrentUser() {
        String userName = authenticationFacade.getAuthentication().getName();
        User currentUser = userRepo.findByUsername(userName);
        return currentUser;
    }
}

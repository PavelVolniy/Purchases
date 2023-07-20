package com.chetverik.controllers;

import com.chetverik.components.IAuthenticationFacade;
import com.chetverik.domain.contract.*;
import com.chetverik.domain.entityes.Supplier;
import com.chetverik.domain.entityes.TypeCompany;
import com.chetverik.domain.entityes.TypeOfPurchase;
import com.chetverik.domain.user.Role;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/contracts")
public class ContractsController {
    private ContractRepository contractRepo;
    private BranchRepo branchRepo;
    private IAuthenticationFacade authenticationFacade;
    private UserRepo userRepo;
    private TypePurchaseRepo typePurchaseRepo;
    private SupplierRepo supplierRepo;
    private TypeCompanyRepo typeCompanyRepo;

    public ContractsController(ContractRepository contractRepository,
                               BranchRepo branchRepo,
                               IAuthenticationFacade authenticationFacade,
                               UserRepo userRepo,
                               TypePurchaseRepo typePurchaseRepo,
                               SupplierRepo supplierRepo,
                               TypeCompanyRepo typeCompanyRepo) {
        this.contractRepo = contractRepository;
        this.branchRepo = branchRepo;
        this.authenticationFacade = authenticationFacade;
        this.userRepo = userRepo;
        this.typePurchaseRepo = typePurchaseRepo;
        this.supplierRepo = supplierRepo;
        this.typeCompanyRepo = typeCompanyRepo;
    }

    @GetMapping()
    public String getContractsList(Model model) {
        Iterable<Contract> contracts = contractRepo.findAll();
        for (Contract item : contracts) {
            System.out.println(item);
        }
        model.addAttribute("names", ContractFieldNames.getContractNames());
        model.addAttribute("contracts", contracts);

        return "contracts";
    }

    @GetMapping("/editContract/{id}")
    public String editContractForm(@PathVariable String id, Model model) {
        Long contractId = Long.valueOf(id);
        Iterable<Contract> contract = contractRepo.findAllById(Collections.singleton(contractId));
        if (contract.iterator().next().getBranch() == getCurrentUser().getBranch() || getCurrentUser().getRoles().contains(Role.ADMIN)){
            model.addAttribute("contract", contract);
            model.addAttribute("types", typePurchaseRepo.findAll());
            return "editContract";
        } else {
            return "redirect:/contracts";
        }
    }

    @GetMapping("/contracts/editContract/del/{id}")
    public String delContract(@PathVariable String id){
        if (id!=null && !id.isEmpty()){
            Long contractId = Long.valueOf(id);
            contractRepo.deleteById(contractId);
        }
        return "redirect:/contracts";
    }

    @PostMapping()
    public String editContract(
            @RequestParam("contractId") Contract contract,
            @RequestParam String nameOfContract,
            @RequestParam String pp_poz_ep,
            @RequestParam("typeOfPurchase") TypeOfPurchase typeOfPurchase,
            @RequestParam String numberOfContract,
            @RequestParam String dateOfContract,
            @RequestParam Double sum,
            @RequestParam String dateOfExecutionContract,
            @RequestParam String nameOfSupplier,
            @RequestParam(defaultValue = "000") int innOfSupplier,
            @RequestParam(defaultValue = "non") String nameTypeOfCompany,
            @RequestParam String numberOfRegistryEntry,
            @RequestParam String additionalAgreement,
            @RequestParam String okdp2,
            @RequestParam String f_i_o
    ) {
        Supplier supplier = new Supplier(innOfSupplier, nameOfSupplier);
        TypeCompany typeCompany = new TypeCompany(nameTypeOfCompany);
            supplierRepo.save(supplier);
            typeCompanyRepo.save(typeCompany);

        contract.setNameOfContract(nameOfContract);
        contract.setPp_poz_ep(pp_poz_ep);
        contract.setTypeOfPurchase(typeOfPurchase);
        contract.setNumberOfContract(numberOfContract);
        contract.setDateOfContract(dateOfContract);
        contract.setSum(sum);
        contract.setDateOfExecutionContract(dateOfExecutionContract);
        contract.setSupplier(supplier);
        contract.setTypeOfCompany(typeCompany);
        contract.setNumberOfRegistryEntry(numberOfRegistryEntry);
        contract.setAdditionalAgreement(additionalAgreement);
        contract.setOkdp2(okdp2);
        contract.setF_i_o(f_i_o);
        contractRepo.save(contract);
        return "redirect:/contracts";
    }


    @GetMapping("/addContract")
    public String addContractForm(Model model) {
        model.addAttribute("names", ContractFieldNames.getContractNames());
        model.addAttribute("contractList", ContractFieldNames.getContractNames());
        model.addAttribute("branch", getCurrentUser().getBranch());
        model.addAttribute("types", typePurchaseRepo.findAll());
        System.out.println(typeCompanyRepo.findAll());
        model.addAttribute("typesCompany", typeCompanyRepo.findAll());
        return "addContract";
    }

    @PostMapping("/addContract")
    public String addContract(
            @RequestParam String nameOfContract,
            @RequestParam String pp_poz_ep,
            @RequestParam("typeOfPurchase") TypeOfPurchase typeOfPurchase,
            @RequestParam String numberOfContract,
            @RequestParam String dateOfContract,
            @RequestParam Double sum,
            @RequestParam String dateOfExecutionContract,
            @RequestParam String nameOfSupplier,
            @RequestParam(defaultValue = "000") int innOfSupplier,
            @RequestParam(defaultValue = "non") String nameTypeOfCompany,
            @RequestParam String numberOfRegistryEntry,
            @RequestParam String additionalAgreement,
            @RequestParam String okdp2,
            @RequestParam String f_i_o
    ) {
        Contract newContract;
        Supplier supplier;
        TypeCompany typeCompany;
        if (typeOfPurchase != null && innOfSupplier != 0 && !nameOfSupplier.isEmpty() && !nameTypeOfCompany.isEmpty()) {
            supplier = new Supplier(innOfSupplier, nameOfSupplier);
            typeCompany = new TypeCompany(nameTypeOfCompany);
            supplierRepo.save(supplier);
            typeCompanyRepo.save(typeCompany);
            newContract = new Contract(
                    getCurrentUser().getBranch(),
                    nameOfContract,
                    pp_poz_ep,
                    typeOfPurchase,
                    numberOfContract,
                    dateOfContract,
                    sum,
                    dateOfExecutionContract,
                    supplier,
                    typeCompany,
                    numberOfRegistryEntry,
                    additionalAgreement,
                    okdp2,
                    f_i_o);
            contractRepo.save(newContract);
        }
        return "redirect:/contracts";
    }

    @GetMapping("/clearContractList")
    public String clearContractList() {
        contractRepo.deleteAll();
        return "redirect:/contracts";
    }

    private User getCurrentUser() {
        String userName = authenticationFacade.getAuthentication().getName();
        User currentUser = userRepo.findByUsername(userName);
        return currentUser;
    }
}

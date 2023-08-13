package com.chetverik.controllers;

import com.chetverik.domain.contract.Contract;
import com.chetverik.domain.contract.ContractFieldNames;
import com.chetverik.domain.entityes.Supplier;
import com.chetverik.domain.entityes.TypeCompany;
import com.chetverik.domain.entityes.TypeOfPurchase;
import com.chetverik.domain.purchase.Purchase;
import com.chetverik.domain.user.Role;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.SupplierRepo;
import com.chetverik.repositories.TypeCompanyRepo;
import com.chetverik.repositories.TypePurchaseRepo;
import com.chetverik.service.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/contracts")
@PreAuthorize("hasAuthority('MANAGER')")
public class ContractsController {
    private ContractService contractService;
    private TypePurchaseRepo typePurchaseRepo;
    private SupplierRepo supplierRepo;
    private TypeCompanyRepo typeCompanyRepo;

    @GetMapping()
    public String getContractsList(Model model) {
        List<Contract> contracts = contractService.getAllContracts();
        model.addAttribute("names", ContractFieldNames.getContractNames());
        model.addAttribute("contracts", contracts);

        return "contracts";
    }

    @GetMapping("/editContract/{id}")
    public String editContractForm(@PathVariable String id, Model model, @AuthenticationPrincipal User currentUser) {
        Long contractId = Long.valueOf(id);
        Contract contract = contractService.findContractById(contractId);
        List<TypeCompany> typesCompanyList = contractService.getTypesCompanyList();
        typesCompanyList.remove(contract.getTypeOfCompany());
        List<TypeOfPurchase> typeOfPurchaseList = contractService.getTypeOfPurchaseList();
        typeOfPurchaseList.remove(contract.getTypeOfPurchase());
        if (contract.getUser().equals(currentUser) || currentUser.getRoles().contains(Role.ADMIN)) {
            model.addAttribute("contract", contract);
            model.addAttribute("types", typeOfPurchaseList);
            model.addAttribute("type", contract.getTypeOfPurchase());
            model.addAttribute("typesCompany", typesCompanyList);
            model.addAttribute("typeCompany", contract.getTypeOfCompany());
            return "editContract";
        } else {
            return "redirect:/contracts";
        }
    }

    @GetMapping("/editContract/del/{id}")
    public String delContract(@PathVariable String id,
                              @AuthenticationPrincipal User currentUser) {
        User user = contractService.findContractById(Long.valueOf(id)).getUser();
        if (user.equals(currentUser) || currentUser.getRoles().contains(Role.ADMIN)) {
            if (id != null && !id.isEmpty()) {
                Long contractId = Long.valueOf(id);
                contractService.deleteContractById(contractId);
            }
        }
        return "redirect:/contracts";
    }

    @GetMapping("/addContract/{id}")
    public String addContractFromPurchase(
            @AuthenticationPrincipal User currentUser,
            @PathVariable String id, Model model) {
        Long purchaseId = Long.valueOf(id);
        Purchase purchaseById = contractService.findPurchaseById(purchaseId);
        model.addAttribute("purchase", purchaseById);
        model.addAttribute("branch", currentUser.getBranch());
        model.addAttribute("typesCompany", contractService.getTypesCompanyList());
        return "addContract";
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
            @RequestParam(defaultValue = "not") String nameOfSupplier,
            @RequestParam String innOfSupplier,
            @RequestParam("typeOfCompany") TypeCompany typeOfCompany,
            @RequestParam String numberOfRegistryEntry,
            @RequestParam String additionalAgreement,
            @RequestParam String okdp2,
            @RequestParam String f_i_o,
            @AuthenticationPrincipal User currentUser
    ) {
        if (nameOfSupplier != null && innOfSupplier != null) {
            Supplier supplierByInn = supplierRepo.findByinn(innOfSupplier);
            if (supplierByInn != null) {
                contract.setSupplier(supplierByInn);
            } else {
                Supplier supplier = new Supplier(innOfSupplier, nameOfSupplier);
                contract.setSupplier(supplier);
                supplierRepo.save(supplier);
            }
        }
        contract.setNameOfContract(nameOfContract);
        contract.setPp_poz_ep(pp_poz_ep);
        contract.setTypeOfPurchase(typeOfPurchase);
        contract.setNumberOfContract(numberOfContract);
        contract.setDateOfContract(dateOfContract);
        contract.setSum(sum);
        contract.setDateOfExecutionContract(dateOfExecutionContract);
        contract.setTypeOfCompany(typeOfCompany);
        contract.setNumberOfRegistryEntry(numberOfRegistryEntry);
        contract.setAdditionalAgreement(additionalAgreement);
        contract.setOkdp2(okdp2);
        contract.setF_i_o(f_i_o);
        contract.setDateOfChange(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date()));
        contract.setNameUserToChanged(currentUser.getUsername());
        if (contract.getUser().equals(currentUser) || currentUser.getRoles().contains(Role.ADMIN)) {
            contractService.saveContract(contract);
        }
        return "redirect:/contracts";
    }


    @GetMapping("/addContract")
    public String addContractForm(
            @AuthenticationPrincipal User currentUser,
            Model model) {
        model.addAttribute("names", ContractFieldNames.getContractNames());
        model.addAttribute("contractList", ContractFieldNames.getContractNames());
        model.addAttribute("branch", currentUser.getBranch());
        model.addAttribute("types", typePurchaseRepo.findAll());
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
            @RequestParam(defaultValue = "not") String nameOfSupplier,
            @RequestParam String innOfSupplier,
            @RequestParam("typeOfCompany") TypeCompany typeOfCompany,
            @RequestParam String numberOfRegistryEntry,
            @RequestParam String additionalAgreement,
            @RequestParam String okdp2,
            @RequestParam String f_i_o,
            @AuthenticationPrincipal User currentUser
    ) {
        Contract newContract;
        if (typeOfPurchase != null && !innOfSupplier.isEmpty() && typeOfCompany != null) {
            Supplier supplierByInn = supplierRepo.findByinn(innOfSupplier);
            if (supplierByInn == null) {
                supplierByInn = new Supplier(innOfSupplier, nameOfSupplier);
                supplierRepo.save(supplierByInn);
            }
            newContract = new Contract(
                    currentUser.getBranch(),
                    nameOfContract,
                    pp_poz_ep,
                    typeOfPurchase,
                    numberOfContract,
                    dateOfContract,
                    sum,
                    dateOfExecutionContract,
                    supplierByInn,
                    typeOfCompany,
                    numberOfRegistryEntry,
                    additionalAgreement,
                    okdp2,
                    f_i_o,
                    new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date()),
                    "non",
                    currentUser);
            contractService.saveContract(newContract);
        }
        return "redirect:/contracts";
    }

    @GetMapping("/clearContractList")
    public String clearContractList() {
        contractService.clearContractList();
        return "redirect:/contracts";
    }
}

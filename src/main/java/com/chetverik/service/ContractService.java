package com.chetverik.service;


import com.chetverik.domain.contract.Contract;
import com.chetverik.domain.entityes.Branch;
import com.chetverik.domain.entityes.Supplier;
import com.chetverik.domain.entityes.TypeCompany;
import com.chetverik.domain.entityes.TypeOfPurchase;
import com.chetverik.domain.purchase.Purchase;
import com.chetverik.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ContractService {
    private ContractRepo contractRepo;
    private SupplierRepo supplierRepo;
    private TypePurchaseRepo typePurchaseRepo;
    private BranchRepo branchRepo;
    private TypeCompanyRepo typeCompanyRepo;
    private PurchaseRepo purchaseRepo;

    public List<Contract> getAllContracts() {
        return (List<Contract>) contractRepo.findAll();
    }

    public Contract findContractById(Long id) {
        return contractRepo.findFirstById(id);
    }

    public List<Branch> getBranchList(){
        return (List<Branch>) branchRepo.findAll();
    }

    public List<TypeOfPurchase> getTypeOfPurchaseList() {
        return (List<TypeOfPurchase>) typePurchaseRepo.findAll();
    }

    public List<TypeCompany> getTypesCompanyList() {
        return (List<TypeCompany>) typeCompanyRepo.findAll();
    }

    public void deleteContractById(Long id) {
        contractRepo.deleteById(id);
    }

    public Purchase findPurchaseById(Long id) {
        return purchaseRepo.findById(id).get();
    }

    public Contract getContractByName(String contractName) {
        return contractRepo.findByNameOfContract(contractName);
    }

    public void saveContract(Contract contract) {
        contractRepo.save(contract);
    }

    public void clearContractList() {
        contractRepo.deleteAll();
    }

    public Iterable<Contract> getContractsByParam(Map<String, Object> args) {
        List<Branch> branchList = new ArrayList<>();
        List<TypeOfPurchase> typeOfPurchaseList = new ArrayList<>();
        List<Supplier> supplierList = new ArrayList<>();
        double sumMore = 0;
        double sumLess = 99999999999.0;
        if (args.get("branch") == null) {
            branchList.addAll((Collection<? extends Branch>) branchRepo.findAll());
        } else {
            branchList.add((Branch) args.get("branch"));
        }
        if (args.get("typeOfPurchase") == null) {
            typeOfPurchaseList.addAll((Collection<? extends TypeOfPurchase>) typePurchaseRepo.findAll());
        } else {
            Optional<TypeOfPurchase> typeOfPurchase = typePurchaseRepo.findById(Long.valueOf(args.get("typeOfPurchase").toString()));
            typeOfPurchaseList.add(typeOfPurchase.get());
        }
        if (args.get("nameOfSupplier").toString().isEmpty()) {
            supplierList.addAll((Collection<? extends Supplier>) supplierRepo.findAll());
        } else {
            supplierList.addAll(supplierRepo.getListSuppliersConsistValue((String) args.get("nameOfSupplier")));
        }
        if (!args.get("sumMore").toString().isEmpty()) {
            sumMore = Double.parseDouble(args.get("sumMore").toString());
        }
        if (!args.get("sumLess").toString().isEmpty()) {
            sumLess = Double.parseDouble(args.get("sumLess").toString());
        }
        return contractRepo.listContractsByParam(branchList,
                (String) args.get("nameOfContract"),
                typeOfPurchaseList,
                (String) args.get("numberOfContract"),
                supplierList,
                sumMore,
                sumLess);
    }
}

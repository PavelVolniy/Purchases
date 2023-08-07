package com.chetverik.service;


import com.chetverik.domain.contract.Contract;
import com.chetverik.domain.entityes.Branch;
import com.chetverik.domain.entityes.Supplier;
import com.chetverik.domain.entityes.TypeOfPurchase;
import com.chetverik.repositories.BranchRepo;
import com.chetverik.repositories.ContractRepo;
import com.chetverik.repositories.SupplierRepo;
import com.chetverik.repositories.TypePurchaseRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ContractService {
    private ContractRepo contractRepo;
    private SupplierRepo supplierRepo;
    private TypePurchaseRepo typePurchaseRepo;
    private BranchRepo branchRepo;

    public Iterable<Contract> getAllContracts() {
        return contractRepo.findAll();
    }

    public Contract getContractById(Long id) {
        return contractRepo.findById(id).get();
    }

    public Contract getContractByName(String contractName) {
        return contractRepo.findByNameOfContract(contractName);
    }

    public Iterable<Contract> getContractsByParam(Map<String, Object> args) {
        List<Branch> branchList = new ArrayList<>();
        List<TypeOfPurchase> typeOfPurchaseList = new ArrayList<>();
        List<Supplier> supplierList = new ArrayList<>();
        double sumMore = 0;
        double sumLess = 99999999999.0;
        if (args.get("branch")==null) {
             branchList.addAll((Collection<? extends Branch>) branchRepo.findAll());
        } else {
            branchList.add((Branch) args.get("branch"));
        }
        if (args.get("typeOfPurchase")==null){
            typeOfPurchaseList.addAll((Collection<? extends TypeOfPurchase>) typePurchaseRepo.findAll());
        } else {
            typeOfPurchaseList.add((TypeOfPurchase) args.get("typeOfPurchase"));
        }
        if (args.get("nameOfSupplier").toString().isEmpty()){
            supplierList.addAll((Collection<? extends Supplier>) supplierRepo.findAll());
        } else {
            supplierList.addAll(supplierRepo.getListSuppliersConsistValue((String) args.get("nameOfSupplier")));
        }
        if (!args.get("sumMore").toString().isEmpty()){
            sumMore = Double.parseDouble(args.get("sumMore").toString());
        }
        if (!args.get("sumLess").toString().isEmpty()){
            sumLess = Double.parseDouble(args.get("sumLess").toString());
        }
        System.out.println(branchList);
        System.out.println(typeOfPurchaseList);
        System.out.println(supplierList);
        return contractRepo.listContractsByParam(branchList,
                (String) args.get("nameOfContract"),
                typeOfPurchaseList,
                (String) args.get("numberOfContract"),
                supplierList,
                sumMore,
                sumLess);
    }
}

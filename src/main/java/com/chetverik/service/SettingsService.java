package com.chetverik.service;

import com.chetverik.domain.entityes.Branch;
import com.chetverik.domain.entityes.Supplier;
import com.chetverik.domain.entityes.TypeCompany;
import com.chetverik.domain.entityes.TypeOfPurchase;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class SettingsService {
    private UserRepo userRepo;
    private BranchRepo branchRepo;
    private TypePurchaseRepo typeOfPurchaseRepo;
    private TypeCompanyRepo typeCompanyRepo;
    private SupplierRepo supplierRepo;

    public Iterable<User> getUserList() {
        return userRepo.findAll();
    }

    public Iterable<Branch> getBranchList() {
        return branchRepo.findAll();
    }

    public Iterable<TypeOfPurchase> getTypePurchaseList() {
        return typeOfPurchaseRepo.findAll();
    }

    public Iterable<TypeCompany> getTypeCompanyList() {
        return typeCompanyRepo.findAll();
    }

    public Iterable<Supplier> getSupplierList() {
        return supplierRepo.findAll();
    }


    public Supplier getSupplierByInn(String inn) {
        return supplierRepo.findByinn(inn);
    }

    public Supplier getSupplierById(String id) {
        return supplierRepo.findById(Long.valueOf(id)).get();
    }

    public void saveSupplier(Supplier supplier) {
        supplierRepo.save(supplier);
    }

    public void dellSupplier(String id) {
        supplierRepo.deleteById(Long.valueOf(id));
    }

    public Branch findBranchByName(String branchName) {
        return branchRepo.findByName(branchName);
    }

    public void saveBranch(Branch branch) {
        branchRepo.save(branch);
    }

    public Branch findAllBranchById(Long id) {
        return (Branch) branchRepo.findAllById(Collections.singleton(id));
    }

    public void deleteBranchByID(Long id) {
        branchRepo.deleteById(id);
    }

    public void saveTypeOfPurchase(TypeOfPurchase purchase) {
        typeOfPurchaseRepo.save(purchase);
    }

    public void saveTypeCompany(TypeCompany typeCompany) {
        typeCompanyRepo.save(typeCompany);
    }

    public TypeCompany findAllTypeCompanyById(Long id) {
        return (TypeCompany) typeCompanyRepo.findAllById(Collections.singleton(id));
    }

    public void deleteTypCompanyById(Long id) {
        typeCompanyRepo.deleteById(id);
    }

    public TypeOfPurchase findAllTypePurchaseBiId(Long id) {
        return (TypeOfPurchase) typeOfPurchaseRepo.findAllById(Collections.singleton(id));
    }

    public void deleteTypePurchaseById(Long id) {
        typeOfPurchaseRepo.deleteById(id);
    }

}

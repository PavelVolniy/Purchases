package com.chetverik.service;

import com.chetverik.domain.entityes.TypeOfPurchase;
import com.chetverik.domain.purchase.Purchase;
import com.chetverik.domain.user.User;
import com.chetverik.repositories.PurchaseRepo;
import com.chetverik.repositories.TypePurchaseRepo;
import com.chetverik.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {
    private PurchaseRepo purchaseRepo;
    private TypePurchaseRepo typePurchaseRepo;
    private UserRepo userRepo;

    public Purchase findById(Long id){
            return purchaseRepo.findById(id).get();
    }

    public Purchase findByNamePurchase(String namePurchase){
        return purchaseRepo.findByNamePurchase(namePurchase);
    }

    public List<Purchase> getPurchaseList(){
        return (List<Purchase>) purchaseRepo.findAll();
    }

    public void deletePurchaseById(Long id){
        if (id!=null){
            purchaseRepo.deleteById(id);
        }
    }

    public List<TypeOfPurchase> getTypesOfPurchase(){
        return (List<TypeOfPurchase>) typePurchaseRepo.findAll();
    }

    public void savePurchase(Purchase purchase){
        if (purchase!=null){
            purchaseRepo.save(purchase);
        }
    }

    public Purchase findPurchaseById(Long purchaseId) {
        return purchaseRepo.findById(purchaseId).get();
    }

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}

package com.chetverik.service;


import com.chetverik.domain.purchase.Purchase;

public class CheckedContractFields {
    public static boolean check(Purchase contract){
        if (contract != null && contract.getNamePurchase().length() > 0 &&
                contract.getStartPrice() >= 0) {
            return true;
        } else {
            return false;
        }
    }
}

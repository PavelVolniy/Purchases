package com.chetverik.service;


import com.chetverik.domain.Contract;

public class CheckedContractFields {
    public static boolean check(Contract contract){
        if (contract != null && contract.getNameContract().length() > 0 &&
                contract.getPrice() >= 0) {
            return true;
        } else {
            return false;
        }
    }
}

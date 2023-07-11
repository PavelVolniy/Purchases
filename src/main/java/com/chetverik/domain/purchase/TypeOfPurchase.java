package com.chetverik.domain.purchase;

import java.util.ArrayList;

public class TypeOfPurchase {
    public static String ZK = "ЗК";
    public static String ZKMPS = "ЗК (МПС";
    private static ArrayList<String> listTypesPurchase = new ArrayList<>();


    public static ArrayList<String> getListTypesOfPurchase() {
        listTypesPurchase.add(ZK);
        listTypesPurchase.add(ZKMPS);
        return listTypesPurchase;
    }
}

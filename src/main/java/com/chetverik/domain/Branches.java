package com.chetverik.domain;

import java.util.ArrayList;

public class Branches {
    public static String MANICH = "Маныч";
    public static String CENTR = "Центр";
    public static String AKSP = "эксп";
    public static String KALININ = "Калинин";
    public static String PROLETARSK = "Пролетарск";
    private static ArrayList<String> listOfBranches = new ArrayList<>();

    public static ArrayList<String> getListOfBranches() {
        listOfBranches.add(MANICH);
        listOfBranches.add(CENTR);
        listOfBranches.add(AKSP);
        listOfBranches.add(KALININ);
        listOfBranches.add(PROLETARSK);
        return listOfBranches;
    }
}

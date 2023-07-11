package com.chetverik.domain.contract;

import java.util.ArrayList;

public class TypeOfCompany {
    public static String MICROENTERPRISE = "Микропредприятие";
    public static String SMALLBISNESS = "Малое предприятие";
    public static String SELFEMPLOYED = "Самозанятый";
    public static String MEDIUMENTERPRISE = "Среднее предприятие";
    public static String INDIVIDUAL = "физ. лицо";
    public static String NOTINCLUDED = "не входит";
    public static String NOTSUBJECTMSP = "Не является субъектом МСП";
    private static ArrayList<String> listTypeOfCompany = new ArrayList<>();

    public static ArrayList<String> getListTypeOfCompany(){
        listTypeOfCompany.add(MICROENTERPRISE);
        listTypeOfCompany.add(SMALLBISNESS);
        listTypeOfCompany.add(SELFEMPLOYED);
        listTypeOfCompany.add(MEDIUMENTERPRISE);
        listTypeOfCompany.add(INDIVIDUAL);
        listTypeOfCompany.add(NOTINCLUDED);
        listTypeOfCompany.add(NOTSUBJECTMSP);
        return listTypeOfCompany;
    }
}

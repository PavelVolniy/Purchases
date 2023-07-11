package com.chetverik.domain.purchase;

public class PurchaseFieldNames {
    private static String[] purchaseFieldNames = {
            "Филиал",
            "Наименование",
            "Тип",
            "Состоялось",
            "Дата размещения",
            "Дата окончания",
            "Дата рассмотрения",
            "№ Договора",
            "Начальная цена",
            "Заявок подано",
            "Заявок допущ",
            "Цена заявок 1",
            "Цена заявок 2",
            "Разница значений",
            "Цена договора",
            "Экономия",
            "№ процедуры на еис"
    };

    public static String[] getContractNames(){
        return purchaseFieldNames;
    }
}

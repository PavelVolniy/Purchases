package com.chetverik.domain.contract;
public class ContractFieldNames {

    private static final String[] contractNames = {
            "Флиал",
            "Наименование",
            "ПП ПОЗ с ЕП",
            "Вид закупки",
            "№ договора",
            "Дата договора",
            "Сумма",
            "Дата исполнения договора",
            "Наименование поставщика",
            "ИНН Поставщика",
            "Тип предприятия",
            "№ реестровой записи",
            "доп. соглашение",
            "ОКПД2",
            "Ф.И.О."
    };

    public static String[] getContractNames(){
        return contractNames;
    }
}

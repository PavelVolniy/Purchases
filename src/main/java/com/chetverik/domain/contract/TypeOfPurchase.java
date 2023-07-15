package com.chetverik.domain.contract;

import javax.persistence.*;


@Entity
@Table(name = "TypesOfPurchase")
public class TypeOfPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameTypeOfPurchase;

    public TypeOfPurchase() {
    }

    public TypeOfPurchase(String nameTypeOfPurchase) {
        this.nameTypeOfPurchase = nameTypeOfPurchase;
    }

    public String getNameTypeOfPurchase() {
        return nameTypeOfPurchase;
    }

    public void setNameTypeOfPurchase(String nameTypeOfPurchase) {
        this.nameTypeOfPurchase = nameTypeOfPurchase;
    }

    @Override
    public String toString() {
        return "TypeOfPurchase{" +
                "id=" + id +
                ", nameTypeOfPurchase='" + nameTypeOfPurchase + '\'' +
                '}';
    }
}

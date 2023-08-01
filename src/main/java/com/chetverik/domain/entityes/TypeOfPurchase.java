package com.chetverik.domain.entityes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Table(name = "TypesOfPurchase")
public class TypeOfPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameTypeOfPurchase;

    public TypeOfPurchase(String nameTypeOfPurchase) {
        this.nameTypeOfPurchase = nameTypeOfPurchase;
    }

    @Override
    public String toString() {
        return "TypeOfPurchase{" +
                "id=" + id +
                ", nameTypeOfPurchase='" + nameTypeOfPurchase + '\'' +
                '}';
    }

    public String getNameTypeOfPurchase() {
        return nameTypeOfPurchase;
    }

    public void setNameTypeOfPurchase(String nameTypeOfPurchase) {
        this.nameTypeOfPurchase = nameTypeOfPurchase;
    }
}

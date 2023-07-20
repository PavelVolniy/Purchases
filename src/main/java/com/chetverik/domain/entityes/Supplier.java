package com.chetverik.domain.entityes;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    private long inn;
    private String nameSupplier;

    public Supplier() {
    }

    public Supplier(int inn, String nameSupplier) {
        this.inn = inn;
        this.nameSupplier = nameSupplier;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }
}

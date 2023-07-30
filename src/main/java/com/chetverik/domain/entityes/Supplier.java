package com.chetverik.domain.entityes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {
    @Id
    private long inn;
    private String nameSupplier;

    public Supplier(int inn, String nameSupplier) {
        this.inn = inn;
        this.nameSupplier = nameSupplier;
    }
}

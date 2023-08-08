package com.chetverik.domain.entityes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String inn;
    private String nameSupplier;

    public Supplier(String inn, String nameSupplier) {
        this.inn = inn;
        this.nameSupplier = nameSupplier;
    }
}

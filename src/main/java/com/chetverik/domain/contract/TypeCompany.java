package com.chetverik.domain.contract;

import javax.persistence.*;

@Entity
@Table(name = "type_of_company")
public class TypeCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nameOfTypeCompany;

    public TypeCompany() {
    }

    public TypeCompany(String nameOfTypeCompany) {
        this.nameOfTypeCompany = nameOfTypeCompany;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfTypeCompany() {
        return nameOfTypeCompany;
    }

    public void setNameOfTypeCompany(String nameOfTypeCompany) {
        this.nameOfTypeCompany = nameOfTypeCompany;
    }
}

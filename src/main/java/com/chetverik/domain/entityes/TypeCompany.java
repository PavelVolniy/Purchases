package com.chetverik.domain.entityes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "type_of_company")
public class TypeCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nameTypeCompany;


    public TypeCompany(String nameOfTypeCompany) {
        this.nameTypeCompany = nameOfTypeCompany;
    }

    @Override
    public String toString() {
        return "TypeCompany{" +
                "id=" + id +
                ", nameTypeCompany='" + nameTypeCompany + '\'' +
                '}';
    }

    public String getNameTypeCompany() {
        return nameTypeCompany;
    }

    public void setNameTypeCompany(String nameTypeCompany) {
        this.nameTypeCompany = nameTypeCompany;
    }
}

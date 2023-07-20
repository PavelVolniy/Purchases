package com.chetverik.domain.entityes;

import javax.persistence.*;

@Entity
@Table(name = "type_of_company")
public class TypeCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nameTypeCompany;

    public TypeCompany() {
    }

    public TypeCompany(String nameOfTypeCompany) {
        this.nameTypeCompany = nameOfTypeCompany;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameTypeCompany() {
        return nameTypeCompany;
    }

    public void setNameTypeCompany(String nameTypeCompany) {
        this.nameTypeCompany = nameTypeCompany;
    }

    @Override
    public String toString() {
        return "TypeCompany{" +
                "id=" + id +
                ", nameTypeCompany='" + nameTypeCompany + '\'' +
                '}';
    }
}

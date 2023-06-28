package com.chetverik.domain;


import javax.persistence.*;

@Entity
@Table(name = "contracts")

public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameContract;
    private String dateTo;
    private String dateFrom;
    private Double price;


    public Contract() {
    }

    public Contract(String nameContract, String dateTo, String dateFrom, Double price) {
        this.nameContract = nameContract;
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.price = price;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameContract() {
        return nameContract;
    }

    public void setNameContract(String nameContract) {
        this.nameContract = nameContract;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", nameContract='" + nameContract + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", price=" + price +
                '}';
    }
}

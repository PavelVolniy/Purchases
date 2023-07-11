package com.chetverik.domain.contract;


import com.chetverik.domain.Branches;

import javax.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection(targetClass = Branches.class, fetch = FetchType.EAGER)
    @OneToOne()
    private Branch branch;
    private String nameOfContract;
    private String pp_poz_ep;
    private String typeOfPurchase;
    private String numberOfContract;
    private String dateOfContract;
    private Double sum;
    private String dateOfExecutionContract;
    private String nameOfSupplier;
    private String innOfSupplier;
    private String typeOfCompany;
    private String numberOfRegistryEntry;
    private String additionalAgreement;
    private String okdp2;
    private String f_i_o;

    public Contract() {
    }

    public Contract(Branch branch,
                    String nameOfContract,
                    String pp_poz_ep,
                    String typeOfPurchase,
                    String numberOfContract,
                    String dateOfContract,
                    Double sum,
                    String dateOfExecutionContract,
                    String nameOfSupplier,
                    String innOfSupplier,
                    String typeOfCompany,
                    String numberOfRegistryEntry,
                    String additionalAgreement,
                    String okdp2,
                    String f_i_o) {
        this.branch = branch;
        this.nameOfContract = nameOfContract;
        this.pp_poz_ep = pp_poz_ep;
        this.typeOfPurchase = typeOfPurchase;
        this.numberOfContract = numberOfContract;
        this.dateOfContract = dateOfContract;
        this.sum = sum;
        this.dateOfExecutionContract = dateOfExecutionContract;
        this.nameOfSupplier = nameOfSupplier;
        this.innOfSupplier = innOfSupplier;
        this.typeOfCompany = typeOfCompany;
        this.numberOfRegistryEntry = numberOfRegistryEntry;
        this.additionalAgreement = additionalAgreement;
        this.okdp2 = okdp2;
        this.f_i_o = f_i_o;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", nameOfContract='" + nameOfContract + '\'' +
                ", pp_poz_ep='" + pp_poz_ep + '\'' +
                ", typeOfPurchase='" + typeOfPurchase + '\'' +
                ", numberOfContract='" + numberOfContract + '\'' +
                ", dateOfContract='" + dateOfContract + '\'' +
                ", sum=" + sum +
                ", dateOfExecutionContract='" + dateOfExecutionContract + '\'' +
                ", nameOfSupplier='" + nameOfSupplier + '\'' +
                ", innOfSupplier='" + innOfSupplier + '\'' +
                ", typeOfCompany='" + typeOfCompany + '\'' +
                ", numberOfRegistryEntry='" + numberOfRegistryEntry + '\'' +
                ", additionalAgreement='" + additionalAgreement + '\'' +
                ", okdp2='" + okdp2 + '\'' +
                ", f_i_o='" + f_i_o + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getNameOfContract() {
        return nameOfContract;
    }

    public void setNameOfContract(String nameOfContract) {
        this.nameOfContract = nameOfContract;
    }

    public String getPp_poz_ep() {
        return pp_poz_ep;
    }

    public void setPp_poz_ep(String pp_poz_ep) {
        this.pp_poz_ep = pp_poz_ep;
    }

    public String getTypeOfPurchase() {
        return typeOfPurchase;
    }

    public void setTypeOfPurchase(String typeOfPurchase) {
        this.typeOfPurchase = typeOfPurchase;
    }

    public String getNumberOfContract() {
        return numberOfContract;
    }

    public void setNumberOfContract(String numberOfContract) {
        this.numberOfContract = numberOfContract;
    }

    public String getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(String dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getDateOfExecutionContract() {
        return dateOfExecutionContract;
    }

    public void setDateOfExecutionContract(String dateOfExecutionContract) {
        this.dateOfExecutionContract = dateOfExecutionContract;
    }

    public String getNameOfSupplier() {
        return nameOfSupplier;
    }

    public void setNameOfSupplier(String nameOfSupplier) {
        this.nameOfSupplier = nameOfSupplier;
    }

    public String getInnOfSupplier() {
        return innOfSupplier;
    }

    public void setInnOfSupplier(String innOfSupplier) {
        this.innOfSupplier = innOfSupplier;
    }

    public String getTypeOfCompany() {
        return typeOfCompany;
    }

    public void setTypeOfCompany(String typeOfCompany) {
        this.typeOfCompany = typeOfCompany;
    }

    public String getNumberOfRegistryEntry() {
        return numberOfRegistryEntry;
    }

    public void setNumberOfRegistryEntry(String numberOfRegistryEntry) {
        this.numberOfRegistryEntry = numberOfRegistryEntry;
    }

    public String getAdditionalAgreement() {
        return additionalAgreement;
    }

    public void setAdditionalAgreement(String additionalAgreement) {
        this.additionalAgreement = additionalAgreement;
    }

    public String getOkdp2() {
        return okdp2;
    }

    public void setOkdp2(String okdp2) {
        this.okdp2 = okdp2;
    }

    public String getF_i_o() {
        return f_i_o;
    }

    public void setF_i_o(String f_i_o) {
        this.f_i_o = f_i_o;
    }
}

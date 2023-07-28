package com.chetverik.domain.contract;


import com.chetverik.domain.Branches;
import com.chetverik.domain.entityes.Branch;
import com.chetverik.domain.entityes.Supplier;
import com.chetverik.domain.entityes.TypeCompany;
import com.chetverik.domain.entityes.TypeOfPurchase;
import com.chetverik.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection(targetClass = Branches.class, fetch = FetchType.EAGER)
    @OneToOne
    private Branch branch;
    private String nameOfContract;
    private String pp_poz_ep;
    @ElementCollection(targetClass = TypeOfPurchase.class, fetch = FetchType.EAGER)
    @OneToOne
    private TypeOfPurchase typeOfPurchase;
    private String numberOfContract;
    private String dateOfContract;
    private Double sum;
    private String dateOfExecutionContract;
    @ElementCollection(targetClass = Supplier.class, fetch = FetchType.EAGER)
    @OneToOne
    private Supplier supplier;
    @ElementCollection(targetClass = TypeCompany.class, fetch = FetchType.EAGER)
    @OneToOne
    private TypeCompany typeOfCompany;
    private String numberOfRegistryEntry;
    private String additionalAgreement;
    private String okdp2;
    private String f_i_o;

    @ElementCollection(targetClass = User.class, fetch = FetchType.EAGER)
    @OneToOne
    private User user;

    public Contract() {
    }

    public Contract(Branch branch,
                    String nameOfContract,
                    String pp_poz_ep,
                    TypeOfPurchase typeOfPurchase,
                    String numberOfContract,
                    String dateOfContract,
                    Double sum,
                    String dateOfExecutionContract,
                    Supplier supplier,
                    TypeCompany typeOfCompany,
                    String numberOfRegistryEntry,
                    String additionalAgreement,
                    String okdp2,
                    String f_i_o,
                    User user) {
        this.branch = branch;
        this.nameOfContract = nameOfContract;
        this.pp_poz_ep = pp_poz_ep;
        this.typeOfPurchase = typeOfPurchase;
        this.numberOfContract = numberOfContract;
        this.dateOfContract = dateOfContract;
        this.sum = sum;
        this.dateOfExecutionContract = dateOfExecutionContract;
        this.supplier = supplier;
        this.typeOfCompany = typeOfCompany;
        this.numberOfRegistryEntry = numberOfRegistryEntry;
        this.additionalAgreement = additionalAgreement;
        this.okdp2 = okdp2;
        this.f_i_o = f_i_o;
        this.user = user;
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
                ", nameOfSupplier='" + supplier + '\'' +
                ", typeOfCompany='" + typeOfCompany + '\'' +
                ", numberOfRegistryEntry='" + numberOfRegistryEntry + '\'' +
                ", additionalAgreement='" + additionalAgreement + '\'' +
                ", okdp2='" + okdp2 + '\'' +
                ", f_i_o='" + f_i_o + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public TypeOfPurchase getTypeOfPurchase() {
        return typeOfPurchase;
    }

    public void setTypeOfPurchase(TypeOfPurchase typeOfPurchase) {
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public TypeCompany getTypeOfCompany() {
        return typeOfCompany;
    }

    public void setTypeOfCompany(TypeCompany typeOfCompany) {
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

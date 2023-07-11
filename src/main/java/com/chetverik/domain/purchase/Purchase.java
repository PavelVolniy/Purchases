package com.chetverik.domain.purchase;


import com.chetverik.domain.contract.Branch;
import com.chetverik.domain.contract.ContractFieldNames;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @ElementCollection(targetClass = ContractFieldNames.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "branches" , joinColumns = @JoinColumn(name = "branch_id"))
    private String branch;
    private String namePurchase;
    private String typeOfPurchase;
    private boolean conditionOfPurchase;
    private String dateOfPlacement;
    private String dateOfEnd;
    private String dateOfReview;
    private String numberOfContract;
    private Double startPrice;
    private String applicationSubmitted;
    private String applicationAdmitted;
    private double priceApplicationOne;
    private double priceApplicationTwo;
    private double differenceValues;
    private double priceOfContract;
    private double economy;
    private String numberOfProcedureOnEIS;


    public Purchase() {
    }

    public Purchase(String branch,
                    String namePurchase,
                    String type,
                    boolean condition,
                    String dateOfPlacement,
                    String dateOfEnd,
                    String dateOfReview,
                    String numberOfContract,
                    Double startPrice,
                    String applicationSubmitted,
                    String applicationAdmitted,
                    double priceApplicationOne,
                    double priceApplicationTwo,
                    double differenceValues,
                    double priceOfContract,
                    double economy,
                    String numberOfProcedureOnEIS) {
        this.branch = branch;
        this.namePurchase = namePurchase;
        this.typeOfPurchase = type;
        this.conditionOfPurchase = condition;
        this.dateOfPlacement = dateOfPlacement;
        this.dateOfEnd = dateOfEnd;
        this.dateOfReview = dateOfReview;
        this.numberOfContract = numberOfContract;
        this.startPrice = startPrice;
        this.applicationSubmitted = applicationSubmitted;
        this.applicationAdmitted = applicationAdmitted;
        this.priceApplicationOne = priceApplicationOne;
        this.priceApplicationTwo = priceApplicationTwo;
        this.differenceValues = differenceValues;
        this.priceOfContract = priceOfContract;
        this.economy = economy;
        this.numberOfProcedureOnEIS = numberOfProcedureOnEIS;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", nameContract='" + namePurchase + '\'' +
                ", typeOfPurchase='" + typeOfPurchase + '\'' +
                ", condition=" + conditionOfPurchase +
                ", dateOfPlacement='" + dateOfPlacement + '\'' +
                ", dateOfEnd='" + dateOfEnd + '\'' +
                ", dateOfReview='" + dateOfReview + '\'' +
                ", numberOfContract='" + numberOfContract + '\'' +
                ", startPrice=" + startPrice +
                ", applicationSubmitted='" + applicationSubmitted + '\'' +
                ", applicationAdmitted='" + applicationAdmitted + '\'' +
                ", priceApplicationOne=" + priceApplicationOne +
                ", priceApplicationTwo=" + priceApplicationTwo +
                ", differenceValues=" + differenceValues +
                ", priceOfContract=" + priceOfContract +
                ", economy=" + economy +
                ", numberOfProcedureOnEIS='" + numberOfProcedureOnEIS + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getNamePurchase() {
        return namePurchase;
    }

    public void setNamePurchase(String namePurchase) {
        this.namePurchase = namePurchase;
    }

    public String getTypeOfPurchase() {
        return typeOfPurchase;
    }

    public void setTypeOfPurchase(String typeOfPurchase) {
        this.typeOfPurchase = typeOfPurchase;
    }

    public boolean isConditionOfPurchase() {
        return conditionOfPurchase;
    }

    public void setConditionOfPurchase(boolean conditionOfPurchase) {
        this.conditionOfPurchase = conditionOfPurchase;
    }

    public String getDateOfPlacement() {
        return dateOfPlacement;
    }

    public void setDateOfPlacement(String dateOfPlacement) {
        this.dateOfPlacement = dateOfPlacement;
    }

    public String getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(String dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public String getDateOfReview() {
        return dateOfReview;
    }

    public void setDateOfReview(String dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    public String getNumberOfContract() {
        return numberOfContract;
    }

    public void setNumberOfContract(String numberOfContract) {
        this.numberOfContract = numberOfContract;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public String getApplicationSubmitted() {
        return applicationSubmitted;
    }

    public void setApplicationSubmitted(String applicationSubmitted) {
        this.applicationSubmitted = applicationSubmitted;
    }

    public String getApplicationAdmitted() {
        return applicationAdmitted;
    }

    public void setApplicationAdmitted(String applicationAdmitted) {
        this.applicationAdmitted = applicationAdmitted;
    }

    public double getPriceApplicationOne() {
        return priceApplicationOne;
    }

    public void setPriceApplicationOne(double priceApplicationOne) {
        this.priceApplicationOne = priceApplicationOne;
    }

    public double getPriceApplicationTwo() {
        return priceApplicationTwo;
    }

    public void setPriceApplicationTwo(double priceApplicationTwo) {
        this.priceApplicationTwo = priceApplicationTwo;
    }

    public double getDifferenceValues() {
        return differenceValues;
    }

    public void setDifferenceValues(double differenceValues) {
        this.differenceValues = differenceValues;
    }

    public double getPriceOfContract() {
        return priceOfContract;
    }

    public void setPriceOfContract(double priceOfContract) {
        this.priceOfContract = priceOfContract;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public String getNumberOfProcedureOnEIS() {
        return numberOfProcedureOnEIS;
    }

    public void setNumberOfProcedureOnEIS(String numberOfProcedureOnEIS) {
        this.numberOfProcedureOnEIS = numberOfProcedureOnEIS;
    }
}

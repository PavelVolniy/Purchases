package com.chetverik.domain.purchase;


import com.chetverik.domain.Branches;
import com.chetverik.domain.entityes.Branch;
import com.chetverik.domain.entityes.TypeOfPurchase;
import com.chetverik.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection(targetClass = Branches.class, fetch = FetchType.EAGER)
    @OneToOne()
    private Branch branch;
    private String namePurchase;
    @ElementCollection(targetClass = Purchase.class, fetch = FetchType.EAGER)
    @OneToOne()
    private TypeOfPurchase typeOfPurchase;
    private boolean conditionOfPurchase;
    private String dateOfPlacement;
    private String dateOfEnd;
    private String dateOfReview;
    private String numberOfContract;
    private Double startPrice;
    private int applicationSubmitted;
    private int applicationAdmitted;
    private double priceApplicationOne;
    private double priceApplicationTwo;
    private double differenceValues;
    private double priceOfContract;
    private double economy;
    private int numberOfProcedureOnEIS;
    @ElementCollection(targetClass = User.class, fetch = FetchType.EAGER)
    @OneToOne
    private User user;

    public Purchase() {
    }

    public Purchase(Branch branch,
                    String namePurchase,
                    TypeOfPurchase typeOfPurchase,
                    boolean conditionOfPurchase,
                    String dateOfPlacement,
                    String dateOfEnd,
                    String dateOfReview,
                    String numberOfContract,
                    Double startPrice,
                    int applicationSubmitted,
                    int applicationAdmitted,
                    double priceApplicationOne,
                    double priceApplicationTwo,
                    double differenceValues,
                    double priceOfContract,
                    double economy,
                    int numberOfProcedureOnEIS,
                    User user) {
        this.branch = branch;
        this.namePurchase = namePurchase;
        this.typeOfPurchase = typeOfPurchase;
        this.conditionOfPurchase = conditionOfPurchase;
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
        this.user = user;
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

    public String getNamePurchase() {
        return namePurchase;
    }

    public void setNamePurchase(String namePurchase) {
        this.namePurchase = namePurchase;
    }

    public TypeOfPurchase getTypeOfPurchase() {
        return typeOfPurchase;
    }

    public void setTypeOfPurchase(TypeOfPurchase typeOfPurchase) {
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

    public int getApplicationSubmitted() {
        return applicationSubmitted;
    }

    public void setApplicationSubmitted(int applicationSubmitted) {
        this.applicationSubmitted = applicationSubmitted;
    }

    public int getApplicationAdmitted() {
        return applicationAdmitted;
    }

    public void setApplicationAdmitted(int applicationAdmitted) {
        this.applicationAdmitted = applicationAdmitted;
    }

    public void setNumberOfProcedureOnEIS(int numberOfProcedureOnEIS) {
        this.numberOfProcedureOnEIS = numberOfProcedureOnEIS;
    }

    public int getNumberOfProcedureOnEIS() {
        return numberOfProcedureOnEIS;
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
}

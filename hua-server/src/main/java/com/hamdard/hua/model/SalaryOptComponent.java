package com.hamdard.hua.model;

import java.util.Date;

/**
 * /api/#_salaryoptcomponent
 * 
 */

public class SalaryOptComponent {
    private String creditDebitFlag;
    private String description;
    private String empOptOutFlag;
    private Date   endDate;
    private int    frequency;
    private int    optCompId;
    private String optCompName;
    private String salOptComponent;

    public String getCreditDebitFlag() {
        return this.creditDebitFlag;
    }
    public void setCreditDebitFlag(String creditDebitFlag) {
        this.creditDebitFlag = creditDebitFlag;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getEmpOptOutFlag() {
        return this.empOptOutFlag;
    }
    public void setEmpOptOutFlag(String empOptOutFlag) {
        this.empOptOutFlag = empOptOutFlag;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public int getFrequency() {
        return this.frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public int getOptCompId() {
        return this.optCompId;
    }
    public void setOptCompId(int optCompId) {
        this.optCompId = optCompId;
    }
    public String getOptCompName() {
        return this.optCompName;
    }
    public void setOptCompName(String optCompName) {
        this.optCompName = optCompName;
    }
    public String getSalOptComponent() {
        return this.salOptComponent;
    }
    public void setSalOptComponent(String salOptComponent) {
        this.salOptComponent = salOptComponent;
    }
    
    
}



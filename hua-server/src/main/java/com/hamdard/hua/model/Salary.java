package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Salary {

    private long salaryId;
    private String description;
    private long jobRoleId;
    private long salCompId;
    private double salValue;
    private double maxAllowLimit;
    private int salOptCompFlag;

    public long getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(long salaryId) {
        this.salaryId = salaryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(long jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public long getSalCompId() {
        return salCompId;
    }

    public void setSalCompId(long salCompId) {
        this.salCompId = salCompId;
    }

    public double getSalValue() {
        return salValue;
    }

    public void setSalValue(double salValue) {
        this.salValue = salValue;
    }

    public double getMaxAllowLimit() {
        return maxAllowLimit;
    }

    public void setMaxAllowLimit(double maxAllowLimit) {
        this.maxAllowLimit = maxAllowLimit;
    }

    public int getSalOptCompFlag() {
        return salOptCompFlag;
    }

    public void setSalOptCompFlag(int salOptCompFlag) {
        this.salOptCompFlag = salOptCompFlag;
    }

}

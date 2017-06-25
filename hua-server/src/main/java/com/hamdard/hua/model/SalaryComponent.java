package com.hamdard.hua.model;

/**
 * /api/#_salarycomponent
 * 
 */

public class SalaryComponent {
    
    private int    compId;
    private String compName;
    private String description;
    private String salComponent;
    
    public int getCompId() {
        return this.compId;
    }
    public void setCompId(int compId) {
        this.compId = compId;
    }
    public String getCompName() {
        return this.compName;
    }
    public void setCompName(String compName) {
        this.compName = compName;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSalComponent() {
        return this.salComponent;
    }
    public void setSalComponent(String salComponent) {
        this.salComponent = salComponent;
    }
    
}



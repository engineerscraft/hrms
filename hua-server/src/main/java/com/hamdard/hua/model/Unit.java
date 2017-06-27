package com.hamdard.hua.model;

/**
 * Unit
 * /hrms/api/#_unit
 */

public class Unit {
    
    private String address;
    private String empIdPrefix;
    private String empIdSeqName;
    private int    orgId;
    private String orgName;
    private int    unitId;
    private String unitName;
    
    
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmpIdPrefix() {
        return this.empIdPrefix;
    }
    public void setEmpIdPrefix(String empIdPrefix) {
        this.empIdPrefix = empIdPrefix;
    }
    public String getEmpIdSeqName() {
        return this.empIdSeqName;
    }
    public void setEmpIdSeqName(String empIdSeqName) {
        this.empIdSeqName = empIdSeqName;
    }
    public int getOrgId() {
        return this.orgId;
    }
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
    public String getOrgName() {
        return this.orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public int getUnitId() {
        return this.unitId;
    }
    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
    public String getUnitName() {
        return this.unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
        
    @Override
    public String toString() {
        return "Unit [unitId=" + unitId + ", unitName=" + unitName + ", empIdPrefix=" + empIdPrefix + ", empIdSeqName=" + empIdSeqName + ", address=" + address + ", orgId=" + orgId + ", orgName=" + orgName + "]";
    }

    
}


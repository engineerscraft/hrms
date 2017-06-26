package com.hamdard.hua.model;

/**
 * @author Somdeb
 *
 */
public class Unit {
    private int unitId;
    private String unitName;
    private String empIdPrefix;
    private String empIdSeqName;
    private String address;
    private int orgId;
    private String orgName;
    
    /**
     * @return the unitId
     */
    public int getUnitId() {
        return unitId;
    }

    /**
     * @param unitId
     *            the unitId to set
     */
    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    /**
     * @return the unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName
     *            the unitName to set
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * @return the empIdPrefix
     */
    public String getEmpIdPrefix() {
        return empIdPrefix;
    }

    /**
     * @param empIdPrefix the empIdPrefix to set
     */
    public void setEmpIdPrefix(String empIdPrefix) {
        this.empIdPrefix = empIdPrefix;
    }

    /**
     * @return the empIdSeqName
     */
    public String getEmpIdSeqName() {
        return empIdSeqName;
    }

    /**
     * @param empIdSeqName the empIdSeqName to set
     */
    public void setEmpIdSeqName(String empIdSeqName) {
        this.empIdSeqName = empIdSeqName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * @return the orgId
     */
    public int getOrgId() {
        return orgId;
    }

    /**
     * @param orgId the orgId to set
     */
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    @Override
    public String toString() {
        return "Unit [unitId=" + unitId + ", unitName=" + unitName + ", empIdPrefix=" + empIdPrefix + ", empIdSeqName=" + empIdSeqName + ", address=" + address + ", orgId=" + orgId + ", orgName=" + orgName + "]";
    }
}

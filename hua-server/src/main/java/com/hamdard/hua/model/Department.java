package com.hamdard.hua.model;

/**
 * @author Somdeb
 *
 */
public class Department {
    private int departmentId;
    private String departmentName;
    private int parentDepartmentId;
    private String address;
    private int unitId;
    private String unitName;

    /**
     * @return the departmentId
     */
    public int getDepartmentId() {
        return departmentId;
    }



    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }



    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }



    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }



    /**
     * @return the parentDepartmentId
     */
    public int getParentDepartmentId() {
        return parentDepartmentId;
    }



    /**
     * @param parentDepartmentId the parentDepartmentId to set
     */
    public void setParentDepartmentId(int parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
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
     * @return the unitId
     */
    public int getUnitId() {
        return unitId;
    }



    /**
     * @param unitId the unitId to set
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
     * @param unitName the unitName to set
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }



    @Override
    public String toString() {
        return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", parentDepartmentId=" + parentDepartmentId + ", address=" + address + ", unitId=" + unitId + ", unitName=" + unitName + "]";
    }
}

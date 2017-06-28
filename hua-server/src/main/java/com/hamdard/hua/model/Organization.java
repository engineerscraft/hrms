package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Somdeb
 *
 */
@XmlRootElement
public class Organization {
    private int orgId;
    private String orgName;
    private String address;
    private int orgTypeId;
    private String orgTypeName;
    private String description;

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
     * @return the orgTypeId
     */
    public int getOrgTypeId() {
        return orgTypeId;
    }

    /**
     * @param orgTypeId the orgTypeId to set
     */
    public void setOrgTypeId(int orgTypeId) {
        this.orgTypeId = orgTypeId;
    }

    /**
     * @return the orgTypeName
     */
    public String getOrgTypeName() {
        return orgTypeName;
    }

    /**
     * @param orgTypeName the orgTypeName to set
     */
    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Organization {orgId=" + orgId + ", orgName=" + orgName + ", address=" + address + ", orgType {orgTypeId=" + orgTypeId + ", orgTypeName=" + orgTypeName + ", description=" + description + "}}";
    }
}
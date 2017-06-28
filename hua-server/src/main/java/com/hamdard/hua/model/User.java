package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jyotirmoy Banrejee
 * Model object containing user information. 
 * Mainly used while creating an user from the GUI
 */

@XmlRootElement
public class User {
	String                         userName;
	String                         fullName;
	String                         description;
	String                         employeeNumber;
	String                         homePostalAddress;
	String                         mobile;
	String                         initialPassword;
	
	
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getEmployeeNumber() {
        return this.employeeNumber;
    }
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    public String getHomePostalAddress() {
        return this.homePostalAddress;
    }
    public void setHomePostalAddress(String homePostalAddress) {
        this.homePostalAddress = homePostalAddress;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getInitialPassword() {
        return this.initialPassword;
    }
    public void setInitialPassword(String initialPassword) {
        this.initialPassword = initialPassword;
    }
    public String getFullName() {
        return this.fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}



package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.hamdard.hua.enums.AccountType;

@XmlRootElement
public class Account {
    private String accountCode;
    private String accountName;
    private AccountType accountType;
    
    
    
    public Account() {
        super();
    }

    public Account(String accountCode, String accountName, AccountType accountType) {
        super();
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.accountType = accountType;
    }
    
    public String getAccountCode() {
        return accountCode;
    }
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account [accountCode=" + accountCode + ", accountName=" + accountName + ", accountType=" + accountType + "]";
    }
    
    
}

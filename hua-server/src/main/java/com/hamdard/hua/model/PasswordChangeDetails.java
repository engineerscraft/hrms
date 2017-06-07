package com.hamdard.hua.model;

public class PasswordChangeDetails {
    private char[] currentPassword;
    private char[] newPassword;
    
    public PasswordChangeDetails(char[] currentPassword, char[] newPassword) {
        super();
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
    
    public char[] getCurrentPassword() {
        return currentPassword;
    }
    public void setCurrentPassword(char[] currentPassword) {
        this.currentPassword = currentPassword;
    }
    public char[] getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(char[] newPassword) {
        this.newPassword = newPassword;
    }
}

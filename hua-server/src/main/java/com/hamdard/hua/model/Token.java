package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {
    private String accessToken;
    private String refreshToken;
    private String userName;
    private String userDisplayName;

    public Token() {

    }

    public Token(String accessToken, String refreshToken, String userName, String userDisplayName) {
        super();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userName = userName;
        this.userDisplayName = userDisplayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}

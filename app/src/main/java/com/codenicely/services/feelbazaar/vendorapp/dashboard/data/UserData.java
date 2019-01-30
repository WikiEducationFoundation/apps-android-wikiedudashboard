package com.codenicely.services.feelbazaar.vendorapp.dashboard.data;

public class UserData {
    private String username;

    public UserData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                '}';
    }
}

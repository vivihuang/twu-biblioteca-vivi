package com.twu.biblioteca.entity;

public class UserAccount {
    private String number;
    private String password;

    public UserAccount(String number, String password) {

        this.number = number;
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }
}

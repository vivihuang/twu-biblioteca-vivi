package com.twu.biblioteca.entity;

public class UserAccount {
    private String name;
    private String email;
    private int phone;
    private String number;
    private String password;

    public UserAccount(String name, String email, int phone, String number, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.number = number;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }
}

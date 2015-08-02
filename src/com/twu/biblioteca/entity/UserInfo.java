package com.twu.biblioteca.entity;

/**
 * Created by Vivi on 7/30/15.
 */
public class UserInfo {
    private String name;
    private String email;
    private int phone;
    private String number;

    public UserInfo(String name, String email, int phone, String number) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.number = number;
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
}

package com.example.contacts;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name, number, email;
    private int avatar;

    public Contact(String name, String number, String email, int avatar) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}

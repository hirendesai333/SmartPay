package com.example.smartpay.Dto;

public class User {
    private String name;
    private String number;

    public User() {
    }

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return number;
    }

    public void setNum(String number) {
        this.number = number;
    }
}

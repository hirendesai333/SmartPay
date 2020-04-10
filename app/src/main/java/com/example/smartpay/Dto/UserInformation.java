package com.example.smartpay.Dto;

public class UserInformation {

    public String name;
    public String number;
    public String email;

    public UserInformation(){

    }

    public UserInformation(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }
}

package com.example.smartpay.Dto;

public class itemInformation {

    public String name;
    public String price;
    public String weight;
    public String image;
    public String qty;
    public double totalAmount;
    public String itemID;

    public itemInformation(String name, String price, String weight, String qty, double totalAmount,String image,String refNo) {

        this.name = name;
        this.price = price;
        this.weight = weight;
        this.qty = qty;
        this.totalAmount = totalAmount;
        this.image = image;
        this.itemID = refNo;

    }


}

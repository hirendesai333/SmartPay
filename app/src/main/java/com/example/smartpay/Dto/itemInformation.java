package com.example.smartpay.Dto;

public class itemInformation {

    public double totalAmount;
    public String itemID;
    public String name;
    public String price;
    public String weight;
    public String image;
    public String qty;

    public itemInformation(String itemID, String name,String price,String weight,String qty,double totalAmount,String image ) {

        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.qty = qty;
        this.totalAmount = totalAmount;
        this.image = image;
    }

}

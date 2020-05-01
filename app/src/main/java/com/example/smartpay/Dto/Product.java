package com.example.smartpay.Dto;

import androidx.annotation.NonNull;

public class Product {
    private String ItemName;
    private String Price;
    private String Weight;
    private String Qty;
    private int TotalPrice;
    private String PaymentRefNo;

    public Product() {
    }

    public Product(String itemName, String price, String weight, String qty, int totalPrice, String paymentRefNo) {
        this.ItemName = itemName;
        this.Price = price;
        this.Weight = weight;
        this.Qty = qty;
        this.TotalPrice = totalPrice;
        this.PaymentRefNo = paymentRefNo;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public void setPaymentRefNo(String paymentRefNo) {
        PaymentRefNo = paymentRefNo;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getPrice() {
        return Price;
    }

    public String getWeight() {
        return Weight;
    }

    public String getQty() {
        return Qty;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public String getPaymentRefNo() {
        return PaymentRefNo;
    }


}

package com.example.smartpay.Dto;

public class Product {
    private String ItemName;
    private String Price;
    private String Weight;
    private String Qty;
    private String TotalPrice;
    private String PaymentRefNo;

    public Product() {
    }

    public Product(String itemName, String price, String weight, String qty, String totalPrice, String paymentRefNo) {
        this.ItemName = itemName;
        this.Price = price;
        this.Weight = weight;
        this.Qty = qty;
        this.TotalPrice = totalPrice;
        this.PaymentRefNo = paymentRefNo;
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

    public String getTotalPrice() {
        return "₹ "+TotalPrice;
    }

    public String getPaymentRefNo() {
        return PaymentRefNo;
    }

}

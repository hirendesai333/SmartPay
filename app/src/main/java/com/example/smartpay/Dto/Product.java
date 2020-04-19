package com.example.smartpay.Dto;

public class Product {
    private String ItemName;
    private String Price;
    private String Weight;
    private String Qty;
    private String TotalPrice;
    private String ItemImage;
    private String PaymentRefNo;

    public Product() {
    }

    public Product(String itemName, String price, String weight, String qty, String totalPrice, String itemImage, String paymentRefNo) {
        ItemName = itemName;
        Price = price;
        Weight = weight;
        Qty = qty;
        TotalPrice = totalPrice;
        ItemImage = itemImage;
        PaymentRefNo = paymentRefNo;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getItemImage() {
        return ItemImage;
    }

    public void setItemImage(String itemImage) {
        ItemImage = itemImage;
    }

    public String getPaymentRefNo() {
        return(PaymentRefNo);
    }

    public void setPaymentRefNo(String paymentRefNo) {
        PaymentRefNo = paymentRefNo;
    }
}

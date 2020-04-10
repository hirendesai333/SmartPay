package com.example.smartpay.Dto;

public class OrderedListItem {

    private String ItemImage;
    private String ItemName;
    private String PaymentReNo;
    private String Price;
    private String Qty;
    private String TotalPrice;
    private String Weight;

    public OrderedListItem(String itemImage, String itemName, String paymentReNo, String price, String qty, String totalPrice, String weight) {
        ItemImage = itemImage;
        ItemName = itemName;
        PaymentReNo = paymentReNo;
        Price = price;
        Qty = qty;
        TotalPrice = totalPrice;
        Weight = weight;
    }

    public String getItemImage() {
        return ItemImage;
    }

    public void setItemImage(String itemImage) {
        ItemImage = itemImage;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getPaymentReNo() {
        return PaymentReNo;
    }

    public void setPaymentReNo(String paymentReNo) {
        PaymentReNo = paymentReNo;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
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

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }
}

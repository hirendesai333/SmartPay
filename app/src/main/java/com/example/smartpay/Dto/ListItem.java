package com.example.smartpay.Dto;

public class ListItem {

    private String ItemName;
    private String Price;
    private String Weight;
    private String image;
    private String qty;

  /*  public ListItem(String itemPrice){
        this.Price = itemPrice;
    }*/

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQty() {
        return qty;
    }

    public String getName() {
        return ItemName;
    }

    public void setName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public String toString(){
        return ItemName+" "+Price+" "+Weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

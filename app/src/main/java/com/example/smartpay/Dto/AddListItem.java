package com.example.smartpay.Dto;

public class AddListItem {

    private String name;
    private String price;
    private String weight;
    private String image;
    private String qty;

    /*public ListItem() {

    }

    public ListItem(String name, String price, String weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }*/



    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQty() {
        return qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String toString(){
        return name+" "+price+" "+weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

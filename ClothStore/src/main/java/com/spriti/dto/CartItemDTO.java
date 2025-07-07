package com.spriti.dto;

public class CartItemDTO {

    private String name; //"CartItem 1"
    private String prodName;
    private int quantity;
    private double price;

    public CartItemDTO(String name, String prodName, int quantity, double price) {
        this.name = name;
        this.prodName = prodName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

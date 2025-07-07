package com.spriti.dto;

public class OrderItemDto {

    private String prodName;
    private int quantity;
    private double price;

    public OrderItemDto() {

    }

    public OrderItemDto(String prodName, int quantity, double price) {
        this.prodName = prodName;
        this.quantity = quantity;
        this.price = price;
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

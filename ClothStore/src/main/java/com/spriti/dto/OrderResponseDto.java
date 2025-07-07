package com.spriti.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderResponseDto {

    private int orderId;
    private LocalDate orderDate;
    private String status;
    private String shippingAddress;
    private List<OrderItemDto> items;

    public OrderResponseDto() {

    }

    public OrderResponseDto(int orderId, LocalDate orderDate, String status, String shippingAddress, List<OrderItemDto> items) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }
}

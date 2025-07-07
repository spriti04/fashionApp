package com.spriti.dto;

import com.spriti.Model.CartItem;

import java.util.List;

//To Hide User Info and Add Sequential CartItem Numbers
public class CartResponseDTO {
    private int cartId;
    private List<CartItemDTO> cartItemList;
    private double totalPrice;

    public CartResponseDTO(int cartId, List<CartItemDTO> cartItems, double totalPrice){
        this.cartId = cartId;
        this.cartItemList = cartItems;
        this.totalPrice = totalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItemDTO> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemDTO> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

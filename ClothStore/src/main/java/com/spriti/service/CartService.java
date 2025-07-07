package com.spriti.service;

import com.spriti.Model.Cart;
import com.spriti.dto.CartResponseDTO;

public interface CartService {

    public Cart addToCart(int userId, int prodId, Integer quantity);

    public Cart removeFromCart(int userId, int prodId);

    //Helper Method
    public CartResponseDTO convertToCartResponseDTO(Cart cart);

}

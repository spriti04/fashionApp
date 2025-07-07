package com.spriti.controller;

import com.spriti.Model.Cart;
import com.spriti.dto.AddToCart;
import com.spriti.dto.CartResponseDTO;
import com.spriti.service.CartService;
import com.spriti.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @PostMapping("/user/addToCart")
    public ResponseEntity<CartResponseDTO> prodAddToCart(@RequestBody AddToCart request){
        Cart savedCart = cartService.addToCart(request.getPersonId(), request.getProdId(), request.getQuantity());
        CartResponseDTO cartDTO = cartService.convertToCartResponseDTO(savedCart);
        return new ResponseEntity<>(cartDTO, HttpStatus.CREATED);

    }

    @DeleteMapping("/user/removeFromCart")
    public ResponseEntity<?> prodRemoveFromCart(@RequestParam int personId,
                                                @RequestParam int prodId){
        Cart updatedCart = cartService.removeFromCart(personId, prodId);

        if(updatedCart == null){
            return ResponseEntity.ok("Cart deleted because it became empty.");
        }
        CartResponseDTO dto = cartService.convertToCartResponseDTO(updatedCart);
        return ResponseEntity.ok(dto);
    }
}

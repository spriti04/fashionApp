package com.spriti.serviceImpl;

import com.spriti.Model.Cart;
import com.spriti.Model.CartItem;
import com.spriti.Model.Person;
import com.spriti.Model.Product;
import com.spriti.dto.CartItemDTO;
import com.spriti.dto.CartResponseDTO;
import com.spriti.repository.CartItemRepository;
import com.spriti.repository.CartRepository;
import com.spriti.repository.PersonRepository;
import com.spriti.repository.ProductRepository;
import com.spriti.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository itemRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public CartResponseDTO convertToCartResponseDTO(Cart cart) {
        List<CartItemDTO> itemDTOS = new ArrayList<>();
        int index = 1;
        for(CartItem item : cart.getCartItems()){
            itemDTOS.add(new CartItemDTO(
                    "CartItem " + index++,
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getPrice()
            ));
        }

        return new CartResponseDTO(
                cart.getId(),
                itemDTOS,
                cart.getTotalPrice()
        );
    }

    @Override
    public Cart addToCart(int userId, int prodId, Integer quantity) {

        //Default quantity 1
        if(quantity == null || quantity <= 0){
            quantity = 1;
        }

        //Fetch user
        Person person = personRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Get or Create Cart for User
        Cart cart = cartRepository.findByPersonId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setPerson(person);
                    return newCart;
                });

        //Get the Product
        Product product = productRepository.findById(prodId)
                .orElseThrow(() -> new RuntimeException("Product not found with this Id"));


        if(product.getStock() == 0){
            productRepository.delete(product);
            throw new RuntimeException("Product is already out of stock and has been removed");
        }

        //Check Stock Availability
        if(product.getStock() < quantity){
            throw new RuntimeException("Out of stock : Only "+ product.getStock() + " item(s) available");
        }

            //Check if Product is Already in the Cart
            CartItem existingItem = null;
            for (CartItem item : cart.getCartItems()) {
                if (item.getProduct().getId() == prodId) {
                    existingItem = item;
                    break;
                }
            }

            //If Product Exists in CartItem
            if (existingItem != null) {
                //Product Already Exists as a CartItem -> so Update Quantity and Price
                int newQuantity = existingItem.getQuantity() + quantity;
                existingItem.setQuantity(newQuantity);
                existingItem.setPrice(product.getPrice() * newQuantity);
            }
            //If Product Doesn't Exist
            else {
                //Add New Item
                CartItem items = new CartItem();
                items.setProduct(product);
                items.setQuantity(quantity);
                items.setPrice(product.getPrice() * quantity);

                //Set Cart object inside CartItem
                items.setCart(cart);

                //Set CartItem object inside Cart
                cart.getCartItems().add(items);
            }

            product.setStock(product.getStock() - quantity);
            productRepository.save(product);

        //Calculate TotalPrice of Cart class and Set it to Cart
        double totalValue = cart.getCartItems().stream()
                .mapToDouble(CartItem::getPrice).sum();

        cart.setTotalPrice(totalValue);
        Cart savedCart = cartRepository.save(cart);

        return savedCart;
    }

    @Override
    public Cart removeFromCart(int userId, int prodId) {

        //Find Cart by UserId
        Cart cart = cartRepository.findByPersonId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));

        //Find CartItem Object in Which That Product Exists
        CartItem itemRemove = null;
        for(CartItem items : cart.getCartItems()){
            if(items.getProduct().getId() == prodId){
                itemRemove = items;
                break;
            }
        }

        //If not Found that CartItem Object
        if(itemRemove == null){
            throw new RuntimeException("Product is not Present in the Cart");
        }

        //Remove from Cart
        cart.getCartItems().remove(itemRemove);

        //Delete the CartItem Object from DB
        itemRepository.delete(itemRemove);

        //Restore Product Stock
        Product product = itemRemove.getProduct();
        product.setStock(product.getStock() + itemRemove.getQuantity());
        productRepository.save(product);

        if(cart.getCartItems().isEmpty()){
            cartRepository.delete(cart);
            return null;
        }else{
            double totalValue = cart.getCartItems().stream()
                    .mapToDouble(CartItem::getPrice).sum();

            cart.setTotalPrice(totalValue);
            return cartRepository.save(cart);
        }
    }


}

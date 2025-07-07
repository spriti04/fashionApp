package com.spriti.serviceImpl;

import com.spriti.Model.*;
import com.spriti.dto.OrderItemDto;
import com.spriti.dto.OrderResponseDto;
import com.spriti.repository.CartRepository;
import com.spriti.repository.OrderRepository;
import com.spriti.repository.PersonRepository;
import com.spriti.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public ProductOrder placeOrder(int userId) {
        //Find User's Cart
        Cart cart = cartRepository.findByPersonId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        //Check if cart is not empty
        if(cart.getCartItems().isEmpty()){
            throw new RuntimeException("Cart is empty. Can't place order");
        }

        //Get User and User's Address from Cart. Use the Address while Ordering
        Person personDetails = cart.getPerson();
        Address address = personDetails.getAddress();
        String fullAddress = "Name: " + personDetails.getName() + " | " +
                "Mobile: " + personDetails.getMobileNum() + " | " +
                "Address: " + address.getCity() + ", " + address.getState() + " - " + address.getPin();

        //Create Product Order
        ProductOrder order = new ProductOrder();
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PLACED);
        order.setShippingAddress(fullAddress);
        order.setCart(cart);

        //Save the order
        ProductOrder savedOrder = orderRepository.save(order);

        //Clear the cart
        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);


        //Remove the Cart After Placing the Order
//        cartRepository.delete(cart);

        return savedOrder;
    }

    @Override
    public ProductOrder cancelOrder(int orderId) {
        ProductOrder productOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        productOrder.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(productOrder);
    }

    @Override
    public List<OrderResponseDto> getOrderList(int userId) {

        //Find the list of orders by ProductOrder -> Cart -> Person -> PersonId
        List<ProductOrder> orders = orderRepository.findByCart_Person_Id(userId);

        //Create an empty list to show List of ordered items
        List<OrderResponseDto> responseList = new ArrayList<>();

        //Run a loop on ProductOrder
        for(ProductOrder order : orders){
            //Get the Cart object From The ProductOrder
            Cart cart = order.getCart();

            //Get the CartItems by Cart -> .getCartItems and map it to the OrderItemDto class
            List<OrderItemDto> itemDtos = cart.getCartItems().stream()
                    .map(item -> new OrderItemDto(
                            item.getProduct().getName(),
                            item.getQuantity(),
                            item.getPrice()
                    )).collect(Collectors.toList());

            //Add the needed property from order class and the List of OrderItemDto to the
            //OrderResponseDto
            OrderResponseDto dto = new OrderResponseDto(
                    order.getId(),
                    order.getOrderDate(),
                    order.getStatus().toString(),
                    order.getShippingAddress(),
                    itemDtos
            );

            //add the orderResponseDto objects into List of OrderResponseDto
            responseList.add(dto);

        }

        //Return the orderResponseDto List
        return responseList;

    }

    @Override
    public ProductOrder updateOrderStatus(int orderId, OrderStatus newStatus) {
        ProductOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with this id"));

        order.setStatus(newStatus);
        return  orderRepository.save(order);
    }

    @Override
    public List<ProductOrder> getAllOrders() {

        List<ProductOrder> orderList = orderRepository.findAll();

        return orderList;
    }
}

package com.spriti.controller;

import com.spriti.Model.OrderStatus;
import com.spriti.Model.ProductOrder;
import com.spriti.dto.OrderResponseDto;
import com.spriti.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/user/placeOrder/{userId}")
    public ResponseEntity<ProductOrder> placeOrder(@PathVariable int userId){
        ProductOrder order = orderService.placeOrder(userId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/user/cancelOrder/{orderId}")
    public ResponseEntity<ProductOrder> cancelOrder(@PathVariable int orderId){
        ProductOrder order = orderService.cancelOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/user/orderList/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(@PathVariable int userId){
        List<OrderResponseDto> orders = orderService.getOrderList(userId);

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/admin/allOrders")
    public ResponseEntity<List<ProductOrder>> allOrderDetails(){
        List<ProductOrder> orderList = orderService.getAllOrders();

        return ResponseEntity.ok(orderList);
    }

    @PutMapping("/admin/updateOrderStatus/{orderId}")
    public ResponseEntity<ProductOrder> updateStatus(@PathVariable int orderId,
                                                     @RequestParam OrderStatus newStatus){
        ProductOrder updatedOrder = orderService.updateOrderStatus(orderId, newStatus);

        return ResponseEntity.ok(updatedOrder);
    }
}

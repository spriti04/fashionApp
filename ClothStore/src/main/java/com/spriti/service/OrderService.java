package com.spriti.service;

import com.spriti.Model.OrderStatus;
import com.spriti.Model.ProductOrder;
import com.spriti.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {

    public ProductOrder placeOrder(int userId);

    public ProductOrder cancelOrder(int orderId);

    public List<OrderResponseDto> getOrderList(int userId);

    public List<ProductOrder> getAllOrders();

    public ProductOrder updateOrderStatus(int orderId, OrderStatus newStatus);
}

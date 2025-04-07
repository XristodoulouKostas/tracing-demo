package com.kchris.aademo.orderservice.services;

import com.kchris.aademo.orderservice.controllers.CreateOrderRequest;
import com.kchris.aademo.orderservice.domain.Order;
import com.kchris.aademo.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(CreateOrderRequest createOrderRequest) {
        // Check if the user exists and is able to order
        // Check if the products exists and the quantity is enough

//        Order order = Order
        return null;
    }

}

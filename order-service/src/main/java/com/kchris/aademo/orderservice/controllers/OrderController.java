package com.kchris.aademo.orderservice.controllers;

import com.kchris.aademo.orderservice.domain.Order;
import com.kchris.aademo.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(CreateOrderRequest createOrderRequest) {
        return orderService.createOrder(createOrderRequest);
    }
}

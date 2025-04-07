package com.kchris.aademo.orderservice.services;

import com.kchris.aademo.orderservice.controllers.CreateOrderRequest;
import com.kchris.aademo.orderservice.domain.Order;
import com.kchris.aademo.orderservice.enums.OrderStatus;
import com.kchris.aademo.orderservice.repositories.OrderRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order createOrder(CreateOrderRequest createOrderRequest) {
    // TODO - Check if the user exists and is able to order
    // TODO - Check if the products exists and the quantity is enough

    Order order = Order.builder()
        .id(UUID.randomUUID())
        .status(OrderStatus.PENDING)
        .userId(createOrderRequest.userId())
        .productId(createOrderRequest.productId())
        .quantity(createOrderRequest.quantity())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

    orderRepository.insert(order);
    return order;
  }

}

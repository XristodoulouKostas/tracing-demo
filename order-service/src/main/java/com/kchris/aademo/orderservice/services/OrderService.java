package com.kchris.aademo.orderservice.services;

import com.kchris.aademo.orderservice.clients.product.ProductService;
import com.kchris.aademo.orderservice.clients.users.UserService;
import com.kchris.aademo.orderservice.controllers.CreateOrderRequest;
import com.kchris.aademo.orderservice.domain.Order;
import com.kchris.aademo.orderservice.domain.events.OrderCreatedEvent;
import com.kchris.aademo.orderservice.enums.OrderStatus;
import com.kchris.aademo.orderservice.repositories.OrderRepository;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductService productService;
  private final UserService userService;
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public Order createOrder(CreateOrderRequest createOrderRequest) {
    userService.verifyThatUserIsAllowedToOrder(createOrderRequest.userId());
    productService.verifyThereAreEnoughItems(createOrderRequest.productId(), createOrderRequest.quantity());

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
    kafkaTemplate.send("order-created", OrderCreatedEvent.forOrder(order));
    return order;
  }

}

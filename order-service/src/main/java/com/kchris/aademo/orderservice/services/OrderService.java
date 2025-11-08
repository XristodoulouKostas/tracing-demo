package com.kchris.aademo.orderservice.services;

import com.kchris.aademo.orderservice.clients.product.ProductService;
import com.kchris.aademo.orderservice.clients.users.UserService;
import com.kchris.aademo.orderservice.controllers.CreateOrderRequest;
import com.kchris.aademo.orderservice.domain.Order;
import com.kchris.aademo.orderservice.domain.events.OrderCreatedEvent;
import com.kchris.aademo.orderservice.enums.OrderStatus;
import com.kchris.aademo.orderservice.repositories.OrderRepository;
import commonutilities.ArtificialError;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductService productService;
  private final UserService userService;
  private final KafkaTemplate<String, Object> kafkaTemplate;

  private final ArtificialError artificialError = new ArtificialError();

  public Order createOrder(CreateOrderRequest createOrderRequest) {
    artificialError.randomlyFailWith(0.1, new RuntimeException("Could not send the notification"));
    artificialError.randomlyAddDelay(0.05, 10, ChronoUnit.SECONDS);
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

    artificialError.randomlyFailWith(0.1, new RuntimeException("Could not send the notification"));
    artificialError.randomlyAddDelay(0.05, 10, ChronoUnit.SECONDS);
    orderRepository.insert(order);
    log.info("Order {} created successfully", order.id());
    artificialError.randomlyFailWith(0.1, new RuntimeException("Could not send the notification"));
    artificialError.randomlyAddDelay(0.05, 10, ChronoUnit.SECONDS);
    kafkaTemplate.send("order-created", OrderCreatedEvent.forOrder(order));
    return order;
  }

}

package com.kchris.aademo.orderservice.controllers;

import com.kchris.aademo.orderservice.domain.Order;
import com.kchris.aademo.orderservice.services.OrderService;
import io.micrometer.tracing.BaggageInScope;
import io.micrometer.tracing.Tracer;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("orders")
public class OrderController {

  private final Tracer tracer;
  private final OrderService orderService;

  public OrderController(Tracer tracer, OrderService orderService) {
    this.tracer = tracer;
    this.orderService = orderService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Order createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
    addUserIdBaggage(createOrderRequest.userId());
    log.info("Creating order {}", createOrderRequest);
    return orderService.createOrder(createOrderRequest);
  }

  // TODO Test
  private void addUserIdBaggage(UUID userId) {
    try (BaggageInScope baggage = tracer.createBaggageInScope("user-id", userId.toString())) {
    }
  }
}

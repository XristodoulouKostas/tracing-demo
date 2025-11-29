package com.kchris.aademo.orderservice.controllers;

import com.kchris.aademo.orderservice.domain.Order;
import com.kchris.aademo.orderservice.services.OrderService;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        try (Scope scope = addUserIdBaggage(createOrderRequest.userId())) {
            log.info("Creating order {}", createOrderRequest);
            return orderService.createOrder(createOrderRequest);
        }
    }

    private Scope addUserIdBaggage(UUID userId) {
        Baggage baggage = Baggage.current().toBuilder().put("userId", userId.toString()).build();
        return baggage.storeInContext(Context.current()).makeCurrent();
    }
}

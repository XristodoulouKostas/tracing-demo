package com.kchris.aademo.orderservice.domain.events;

import com.kchris.aademo.orderservice.domain.Order;
import com.kchris.aademo.orderservice.enums.OrderStatus;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;

@Builder
public record OrderCreatedEvent(
    UUID id,
    OrderStatus status,
    UUID userId,
    UUID productId,
    int quantity,
    Instant createdAt
) {

  public static OrderCreatedEvent forOrder(Order order) {
    return OrderCreatedEvent.builder()
        .id(order.id())
        .status(order.status())
        .userId(order.userId())
        .productId(order.productId())
        .quantity(order.quantity())
        .createdAt(order.createdAt())
        .build();
  }
}

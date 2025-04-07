package com.kchris.aademo.orderservice.domain;

import com.kchris.aademo.orderservice.enums.OrderStatus;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record Order(
        UUID id,
        OrderStatus status,
        UUID userId,
        UUID productId,
        int quantity,
        Instant createdAt,
        Instant updatedAt
) {
}

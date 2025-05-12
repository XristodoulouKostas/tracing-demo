package com.kchris.aademo.notificationservice.domain.events;

import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(
    UUID id,
    String status,
    UUID userId,
    UUID productId,
    int quantity,
    Instant createdAt
) {

}

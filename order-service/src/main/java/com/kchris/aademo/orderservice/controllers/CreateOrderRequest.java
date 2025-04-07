package com.kchris.aademo.orderservice.controllers;

import java.util.UUID;

public record CreateOrderRequest(
    UUID userId,
    UUID productId,
    int quantity
) {

}

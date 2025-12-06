package com.kchris.aademo;

import java.util.UUID;

record CreateOrderRequest(UUID userId, UUID productId, int quantity) {

  public CreateOrderRequest(String userId, String productId, int quantity) {
    this(UUID.fromString(userId), UUID.fromString(productId), quantity);
  }

  String toJsonString() {
    return """
        {
          "userId": "%s",
          "productId": "%s",
          "quantity": %d
        }
        """.formatted(userId, productId, quantity);
  }

}

package com.kchris.aademo;

import java.util.UUID;

record CreateOrderRequest(UUID userId, UUID productId, int quantity) {

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

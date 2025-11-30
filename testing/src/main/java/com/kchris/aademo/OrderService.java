package com.kchris.aademo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

class OrderService {

  CompletableFuture<HttpResponse<String>> createOrder(CreateOrderRequest req) {
    try (HttpClient client = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("http://localhost:8080/orders"))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(req.toJsonString()))
          .build();

      return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
  }

}

package com.kchris.aademo;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {

  public static void main(String[] args) {
    OrderService orderService = new OrderService();

    List<CompletableFuture<HttpResponse<String>>> futures = getRequests().stream()
        .map(orderService::createOrder)
        .toList();

    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

    futures.forEach(future -> {
      HttpResponse<String> response = future.join();
      printResponse(response);
    });
  }

  private static void printResponse(HttpResponse<String> response) {
    if (response.statusCode() == 201) {
      System.out.printf("201 %n%s %n%s%n%n", response.headers(), response.body());
    } else {
      System.err.printf("%s %n%s %n%s%n%n", response.statusCode(), response.headers(),
          response.body());
    }
  }

  private static List<CreateOrderRequest> getRequests() {
    return List.of(
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac120001",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac120001", 100),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac120001",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac120001", 20),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac120002", // Fail - user
            "f1e2d3c4-b5a6-11ec-9fea-0242ac120001", 20),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac120003",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac120001", 20),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac120003",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac120004", 85), // Fail - product quantity
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac120003",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac120003",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac120004", // Fail - user
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b", // Fail - Unknown product
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12111b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12011b", // Fail - user
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1),
        new CreateOrderRequest("a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
            "f1e2d3c4-b5a6-11ec-9fea-0242ac12000b", 1)
    );
  }
}
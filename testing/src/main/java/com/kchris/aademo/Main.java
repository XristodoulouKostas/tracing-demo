package com.kchris.aademo;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Main {

  public static void main(String[] args) {
    OrderService orderService = new OrderService();

    List<CompletableFuture<HttpResponse<String>>> futures = getUsers().stream()
        .map(user -> orderService.createOrder(
            new CreateOrderRequest(
                UUID.fromString(user),
                UUID.fromString("f1e2d3c4-b5a6-11ec-9fea-0242ac120001"),
                1
            )))
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

  private static List<String> getUsers() {
    return List.of(
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120001",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120002",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120003",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120004",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120005",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120113",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120005",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120006",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac127776",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120007",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120008",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac120009",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac12000a",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac12000c",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac12000b",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac12000c",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac12000d",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac12000e",
        "a1b2c3d4-e5f6-11ec-8fea-0242ac12000f"
    );
  }

}
package com.kchris.aademo.orderservice.clients.product;

import java.util.UUID;

public record Product(UUID id, String name, int quantity) {

}

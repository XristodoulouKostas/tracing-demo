package com.kchris.aademo.productservice.domain;

import java.util.UUID;

public record Product(
    UUID id,
    String name,
    int quantity
) {

}

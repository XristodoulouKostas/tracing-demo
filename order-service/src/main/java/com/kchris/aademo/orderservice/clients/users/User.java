package com.kchris.aademo.orderservice.clients.users;

import java.util.UUID;

public record User(UUID id, String name, String status) {

}

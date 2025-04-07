package com.kchris.aademo.orderservice.repositories;

import com.kchris.aademo.orderservice.domain.Order;
import com.kchris.aademo.orderservice.exceptions.OrderAlreadyExistsException;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderRepository {

    private Map<UUID, Order> orders = new HashMap<>();

    public void insert(Order order) {
        Assert.notNull(order, "Entity cannot be null");
        if (orders.containsKey(order.id())) {
            throw new OrderAlreadyExistsException("Order '%s' already exists");
        }
        orders.put(order.id(), order);
    }
}

package com.kchris.aademo.orderservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
}

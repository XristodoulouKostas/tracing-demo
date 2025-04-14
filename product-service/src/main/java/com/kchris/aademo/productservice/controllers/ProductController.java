package com.kchris.aademo.productservice.controllers;

import com.kchris.aademo.productservice.domain.Product;
import com.kchris.aademo.productservice.services.ProductService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") UUID id) {
    return service.getProductById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}


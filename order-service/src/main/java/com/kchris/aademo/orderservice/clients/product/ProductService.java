package com.kchris.aademo.orderservice.clients.product;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProductService {

  private final RestClient productClient;

  public ProductService(@Qualifier("productServiceRestClient") RestClient productClient) {
    this.productClient = productClient;
  }

  public void verifyThereAreEnoughItems(UUID productId, int quantity) {
    Product product = productClient.get()
        .uri("/products/{id}", productId)
        .retrieve()
        .body(Product.class);

    if (product == null || product.quantity() < quantity) {
      throw new IllegalStateException("Product not available in requested quantity.");
    }
  }

}

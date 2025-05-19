package com.kchris.aademo.orderservice.clients.product;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class ProductService {

  private final RestClient productClient;

  public ProductService(@Qualifier("productServiceRestClient") RestClient productClient) {
    this.productClient = productClient;
  }

  public void verifyThereAreEnoughItems(UUID productId, int quantity) {
    log.debug("Verifying that there are enough products with id '{}'", productId);
    Product product = productClient.get()
        .uri("/products/{id}", productId)
        .retrieve()
        .body(Product.class);

    if (product == null || product.quantity() < quantity) {
      log.error("Product '{}' is not available at the requested quantities ({})", productId, quantity);
      throw new IllegalStateException("Product not available in requested quantity.");
    }
  }

}

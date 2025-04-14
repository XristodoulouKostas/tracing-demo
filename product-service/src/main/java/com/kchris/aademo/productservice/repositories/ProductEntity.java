package com.kchris.aademo.productservice.repositories;

import com.kchris.aademo.productservice.domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductEntity {

  @Id
  private UUID id;
  private String name;
  private int quantity;

  public static ProductEntity fromDomain(Product product) {
    return new ProductEntity(product.id(), product.name(), product.quantity());
  }

  public Product toDomain() {
    return new Product(this.id, this.name, this.quantity);
  }
}

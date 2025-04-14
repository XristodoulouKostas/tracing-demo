package com.kchris.aademo.productservice.services;

import com.kchris.aademo.productservice.domain.Product;
import com.kchris.aademo.productservice.repositories.ProductEntity;
import com.kchris.aademo.productservice.repositories.ProductRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;

  public Optional<Product> getProductById(UUID id) {
    return repository.findById(id).map(ProductEntity::toDomain);
  }

}

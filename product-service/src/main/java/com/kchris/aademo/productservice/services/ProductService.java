package com.kchris.aademo.productservice.services;

import com.kchris.aademo.productservice.domain.Product;
import com.kchris.aademo.productservice.repositories.ProductEntity;
import com.kchris.aademo.productservice.repositories.ProductRepository;
import commonutilities.ArtificialError;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;

  private final ArtificialError artificialError = new ArtificialError();

  public Optional<Product> getProductById(UUID id) {
    artificialError.randomlyFailWith(0.01,
        new RuntimeException("Could not connect to the database"));
    artificialError.randomlyAddDelay(0.05, 10, ChronoUnit.SECONDS);
    return repository.findById(id).map(ProductEntity::toDomain);
  }

}

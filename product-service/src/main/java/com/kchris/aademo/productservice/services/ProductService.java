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
    artificialError.randomlyAddDelay(0.05, 5, ChronoUnit.SECONDS);
    artificialError.randomlyFailWith(0.2, "Could not retrieve products from DB");
    return repository.findById(id).map(ProductEntity::toDomain);
  }

}

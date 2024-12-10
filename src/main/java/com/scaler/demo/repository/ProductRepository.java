package com.scaler.demo.repository;

import com.scaler.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product save(Product product);

    public Optional<Product> findById(Long id);

}

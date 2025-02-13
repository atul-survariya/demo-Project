package com.scaler.demo.service;

import com.scaler.demo.models.Product;
import com.scaler.demo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(String keyword, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return productRepository.findByTitleContaining(keyword, pageable);
    }
}

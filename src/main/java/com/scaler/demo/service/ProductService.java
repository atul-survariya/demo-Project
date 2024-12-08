package com.scaler.demo.service;

import com.scaler.demo.models.Category;
import com.scaler.demo.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductDetails(Long id);

    public Product createProduct(String title, String description, String image, double price, String category);

    public List<Product> getAllProducts();

    public List<Category> getAllCategories();
}

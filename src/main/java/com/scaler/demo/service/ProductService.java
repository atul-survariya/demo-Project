package com.scaler.demo.service;

import com.scaler.demo.exceptions.ProductNotFoundException;
import com.scaler.demo.models.Category;
import com.scaler.demo.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductDetails(Long id) throws ProductNotFoundException;

    public Product createProduct(String title, String description, String image, double price, String category);

    public List<Product> getAllProducts();

    public List<Category> getAllCategories();

    public Product deleteProduct(Long id);

    public Product updateProduct(Long id,String title, String description, String image, double price, String category) throws ProductNotFoundException;
}

package com.scaler.demo.service;

import com.scaler.demo.exceptions.ProductNotFoundException;
import com.scaler.demo.models.Category;
import com.scaler.demo.models.Product;
import com.scaler.demo.repository.CategoryRepository;
import com.scaler.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class DataBaseProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public DataBaseProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductDetails(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return product.get();
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String categoryName) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category categoryFromDataBase = categoryRepository.findByName(categoryName);

        if(categoryFromDataBase == null) {
            Category newCategory = new Category();
            newCategory.setName(categoryName);
            categoryFromDataBase= newCategory;
            //categoryFromDataBase = categoryRepository.save(newCategory);
        }

        product.setCategory(categoryFromDataBase);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}

package com.scaler.demo;

import com.scaler.demo.models.Category;
import com.scaler.demo.models.Product;
import com.scaler.demo.repository.CategoryRepository;
import com.scaler.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        List<String>products = productRepository.getProductData1(3L);
        System.out.println(products);
    }

    @Test
    void test2(){
        List<Category> categories = categoryRepository.getAllCategories();
        for (Category category : categories) {
            System.out.println(category.getName());
        }
        //System.out.println(categories);
    }

    @Test
    void test3(){
        Optional<Product> productOptional= productRepository.getProduct(1L);
        System.out.println(productOptional.get().getId()+ " "+ productOptional.get().getTitle());
    }
}

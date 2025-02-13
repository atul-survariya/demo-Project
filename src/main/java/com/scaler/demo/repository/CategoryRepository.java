package com.scaler.demo.repository;

import com.scaler.demo.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByName(String name);

    public Category save(Category category);

    public List<Category> findAll();

    @Query(value = "select * from category" , nativeQuery = true)
    public List<Category> getAllCategories();
}

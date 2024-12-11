package com.scaler.demo.repository;

import com.scaler.demo.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByName(String name);

    public Category save(Category category);

    public List<Category> findAll();
}

package com.scaler.demo.service;

import com.scaler.demo.exceptions.ProductNotFoundException;
import com.scaler.demo.models.Category;
import com.scaler.demo.models.Product;
import com.scaler.demo.repository.CategoryRepository;
import com.scaler.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
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
        Sort sortDesc = Sort.by(Sort.Direction.DESC, "id");
        return productRepository.findAll(sortDesc);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Product deleteProduct(Long id) {
        return productRepository.deleteProductById(id);
    }

    @Override
    public Product updateProduct(Long id,String title, String description, String image, double price, String category) throws ProductNotFoundException{
    //in ProductRepository what will be name of method,,,,,,,if save() then how we will get id tru input url
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product productToUpdate = product.get();
            productToUpdate.setTitle(title);
            productToUpdate.setDescription(description);
            productToUpdate.setImageUrl(image);
            productToUpdate.setPrice(price);

            Category categoryFromDataBase = categoryRepository.findByName(category);

            //
            if(category==null){
                Category newCategory = new Category();
                //newCategory.setName(categoryFromDataBase.getName());
                newCategory.setName(productToUpdate.getCategory().getName());
                productToUpdate.setCategory(newCategory);
            }else {
                Category newCategory = new Category();
                newCategory.setName(category);
                productToUpdate.setCategory(newCategory);
            }
            return productRepository.save(productToUpdate);
        }else {
            throw new ProductNotFoundException("This Product is not present");
        }
    }
}

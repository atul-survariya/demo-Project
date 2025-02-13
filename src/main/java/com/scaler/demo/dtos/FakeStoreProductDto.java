package com.scaler.demo.dtos;

import com.scaler.demo.models.Category;
import com.scaler.demo.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;

    public Product toProduct(){
        Product product=new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setPrice(Double.parseDouble(getPrice()));
        product.setImageUrl(getImage());

        Category category = new Category();
        category.setName(getCategory());

        product.setCategory(category);
        return product;
    }
}

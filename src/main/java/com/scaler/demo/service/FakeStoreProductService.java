package com.scaler.demo.service;

import com.scaler.demo.dtos.FakeStoreCategoryDto;
import com.scaler.demo.dtos.FakeStoreCreateProductDto;
import com.scaler.demo.dtos.FakeStoreProductDto;
import com.scaler.demo.models.Category;
import com.scaler.demo.models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductDetails(Long id) {
//      FakeStoreProductDto responseDto=
//                restTemplate.getForObject(
//                        "https://fakestoreapi.com/products/" + id,
//                        FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity=
                restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);

        if(responseEntity.getStatusCode() == HttpStatusCode.valueOf(400)){
            // show some error message to FE
        }else if (responseEntity.getStatusCode() == HttpStatusCode.valueOf(500)){
            // tell FE that BE is not working
        }

        return responseEntity.getBody().toProduct();
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String category) {
        FakeStoreCreateProductDto requestDto=new FakeStoreCreateProductDto();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setImage(image);
        requestDto.setPrice(price);
        requestDto.setCategory(category);

        FakeStoreProductDto responseDto= restTemplate.postForObject(
                                                "https://fakestoreapi.com/products",
                                                    requestDto,
                                                    FakeStoreProductDto.class);//1:19

        return responseDto.toProduct();
        
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto []responseDto =  restTemplate.getForObject("https://fakestoreapi.com/products",
                                    FakeStoreProductDto[].class);
        List<Product> products=new ArrayList<Product>();

        for(FakeStoreProductDto dto: responseDto){
            products.add(dto.toProduct());
        }
        return products;
    }

    public List<Category> getAllCategories() {
        ResponseEntity<String []> responseEntity =
                restTemplate.getForEntity("https://fakestoreapi.com/products/categories",
                        String[].class);
//        ResponseEntity<FakeStoreCategoryDto []> responseEntity =
//                restTemplate.getForEntity("https://fakestoreapi.com/products/categories",
//                        FakeStoreCategoryDto[].class);

        if(responseEntity.getStatusCode() == HttpStatusCode.valueOf(400)){
            //
        }else if(responseEntity.getStatusCode() == HttpStatusCode.valueOf(500)){
            //
        }

        List<Category> categories = new ArrayList<>();
        List<FakeStoreCategoryDto> categoriesDto= new ArrayList<>();
        for(String name : responseEntity.getBody()){
            FakeStoreCategoryDto dto= new FakeStoreCategoryDto();
            dto.setName(name);
            categoriesDto.add(dto);
        }
        for(FakeStoreCategoryDto dto: categoriesDto){
            Category category=new Category();
            category.setName(dto.getName());
            categories.add(category);
        }
        return categories;
    }
}

package com.scaler.demo.Controller;

import com.scaler.demo.dtos.CreateProductRequestDto;
import com.scaler.demo.dtos.ErrorDto;
import com.scaler.demo.exceptions.ProductNotFoundException;
import com.scaler.demo.models.Category;
import com.scaler.demo.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.scaler.demo.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController( ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();

    }
    //
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") Long id) throws ProductNotFoundException{
        Product product = productService.getProductDetails(id);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatusCode.valueOf(201));
        return responseEntity;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto requestDto) {
        Product product= productService.createProduct(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getImage(),
                requestDto.getPrice(),
                requestDto.getCategory()
        );
        ResponseEntity<Product> responseEntity= new ResponseEntity<>(product, HttpStatusCode.valueOf(201));

        return responseEntity;
    }

    @GetMapping("/category")
    public List<Category> getAllCategory(){
        return productService.getAllCategories();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product product = productService.deleteProduct(id);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatusCode.valueOf(201));

        return responseEntity;
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorDto> handleNPException(){
//        ErrorDto dto= new ErrorDto();
//        dto.setMessage("Something went wrong");
//        ResponseEntity<ErrorDto> response = new ResponseEntity<>(dto, HttpStatusCode.valueOf(501));
//        return response;
//    }
//
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException(){
//        ErrorDto dto= new ErrorDto();
//        dto.setMessage("Product not found Please try again");
//        ResponseEntity<ErrorDto> response = new ResponseEntity<>(dto, HttpStatusCode.valueOf(404));
//        return response;
//
//    }
}

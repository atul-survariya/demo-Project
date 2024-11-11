package Controller;

import models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import service.ProductService;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/products")
    public void getAllProducts() {

    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long id) {
        return productService.getProductDetails(id);

    }

    public void createProduct() {

    }

}

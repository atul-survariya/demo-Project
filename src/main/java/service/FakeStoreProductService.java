package service;

import models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Product getProductDetails(Long id) {
        System.out.println("Hi Atul");
        return null;
    }
}

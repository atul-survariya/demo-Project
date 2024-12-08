package com.scaler.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductDto { // this class will send response to Fake Store API

    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}

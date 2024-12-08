package com.scaler.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto { // this class take response from Front End

    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}

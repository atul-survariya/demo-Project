package com.scaler.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String keyword;
    private int pageNumber;
    private int size;
}

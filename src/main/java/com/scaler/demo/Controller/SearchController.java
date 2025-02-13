package com.scaler.demo.Controller;

import com.scaler.demo.dtos.SearchRequestDto;
import com.scaler.demo.models.Product;
import com.scaler.demo.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService= searchService;
    }

    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.search(searchRequestDto.getKeyword(),
                searchRequestDto.getPageNumber(), searchRequestDto.getSize());
    }
}

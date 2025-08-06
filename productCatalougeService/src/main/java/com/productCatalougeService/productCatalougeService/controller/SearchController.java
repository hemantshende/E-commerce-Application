package com.productCatalougeService.productCatalougeService.controller;


import com.productCatalougeService.productCatalougeService.dtos.SearchProductDto;
import com.productCatalougeService.productCatalougeService.models.Product;
import com.productCatalougeService.productCatalougeService.services.SearchproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    private SearchproductService searchService;

    @PostMapping("/products")
    public Page<Product> searchProducts(@RequestBody SearchProductDto searchRequestDto) {

        return searchService.searchProduct(
                searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize(),
                searchRequestDto.getSortParams()
        );
    }
}

package com.productCatalougeService.productCatalougeService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;  //ToDo: why are we taking as input
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private CategoryDto category;
}

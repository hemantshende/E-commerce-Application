package com.productCatalougeService.productCatalougeService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
        private String title;
        private String description;
        private String category;
        private String image;
        private Long id;
        private Double price;
}

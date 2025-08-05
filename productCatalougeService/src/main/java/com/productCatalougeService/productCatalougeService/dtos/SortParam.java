package com.productCatalougeService.productCatalougeService.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {

    private String sortCriteria;
    private SortType sortType;
}

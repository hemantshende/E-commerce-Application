package com.productCatalougeService.productCatalougeService.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchProductDto {
    private String query;
    private Integer pageNumber;
    private Integer pageSize;
    private List<SortParam> sortParams=new ArrayList<>();
}

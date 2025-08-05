package com.productCatalougeService.productCatalougeService.services;

import com.productCatalougeService.productCatalougeService.dtos.SortParam;
import com.productCatalougeService.productCatalougeService.dtos.SortType;
import com.productCatalougeService.productCatalougeService.models.Product;
import com.productCatalougeService.productCatalougeService.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchproductService {

    @Autowired
    private ProductRepo productRepo;

    public Page<Product> searchProduct(
            String query,
            Integer pageNumber,
            Integer pageSize,
            List<SortParam> sortParams){

        Sort sort=null;

        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC))
                sort = sort.by(sortParams.get(0).getSortCriteria());
            else
                sort = sort.by(sortParams.get(0).getSortCriteria()).descending();

            for(int i=1;i<sortParams.size();i++) {
                if(sortParams.get(i).getSortType().equals(SortType.ASC))
                    sort = sort.and(sort.by(sortParams.get(i).getSortCriteria()));
                else
                    sort = sort.and(sort.by(sortParams.get(i).getSortCriteria()).descending());

            }
        }

        return productRepo.findByNameEquals(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}

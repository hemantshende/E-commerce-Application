package com.productCatalougeService.productCatalougeService.services;

import com.productCatalougeService.productCatalougeService.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    Product createProduct(Product product);

    Product replaceProduct(Product input,Long id);

    List<Product> getAllProducts(); //ToDo : H/W for learners
}

//2 implementation of this interface
//so we have use @Primary ---> Storageproductservice class...
//so there will be no conflict
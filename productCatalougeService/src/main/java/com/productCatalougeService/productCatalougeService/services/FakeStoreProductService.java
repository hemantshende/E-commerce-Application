package com.productCatalougeService.productCatalougeService.services;

import com.productCatalougeService.productCatalougeService.client.FakeStoreApiClient;
import com.productCatalougeService.productCatalougeService.dtos.FakeStoreProductDto;
import com.productCatalougeService.productCatalougeService.models.Category;
import com.productCatalougeService.productCatalougeService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                        FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto =
                fakeStoreProductDtoResponseEntity.getBody();

        if(fakeStoreProductDto != null &&
                fakeStoreProductDtoResponseEntity.getStatusCode() ==
                        HttpStatus.valueOf(200)) {
            return from(fakeStoreProductDto);
        }

        return null;
    }

    @Override
    public Product createProduct(Product input) {
        FakeStoreProductDto fakeStoreProductDtoInput = from(input);

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                restTemplate.postForEntity("https://fakestoreapi.com/products",
                        fakeStoreProductDtoInput, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDtoOutput =
                fakeStoreProductDtoResponseEntity.getBody();

        if(fakeStoreProductDtoOutput != null &&
                fakeStoreProductDtoResponseEntity.getStatusCode() ==
                        HttpStatus.valueOf(200)) {
            return from(fakeStoreProductDtoOutput);
        }

        return null;
    }

    public Product replaceProduct(Product input,Long id) {
        FakeStoreProductDto fakeStoreProductDtoInput = from(input);
        FakeStoreProductDto output = fakeStoreApiClient.replaceFakeStoreProduct(fakeStoreProductDtoInput,id);
        if(output==null) return null;
        return from(output);
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

}

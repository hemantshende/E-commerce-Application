package com.productCatalougeService.productCatalougeService.controller;

import com.productCatalougeService.productCatalougeService.dtos.CategoryDto;
import com.productCatalougeService.productCatalougeService.dtos.ProductDto;
import com.productCatalougeService.productCatalougeService.models.Category;
import com.productCatalougeService.productCatalougeService.models.Product;
import com.productCatalougeService.productCatalougeService.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//@RestController = @Controller + @ResponseBody
// https://www.baeldung.com/spring-bean-scopes
@RestController
public class ProductController {

    @Autowired
    IProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products=productService.getAllProducts();

        if(products==null ||products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<ProductDto> productDtos=products.stream()
                .map(this::from)
                .collect(Collectors.toList());

        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

    //Read for @PathVariable , @RequestParam and @QueryParam
    //https://www.baeldung.com/spring-requestparam-vs-pathvariable
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        // try {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product Id not found");
        }
        Product product = productService.getProductById(productId);
        if (product == null) return null;

        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
//        }catch (IllegalArgumentException exception){
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
    }

    @PostMapping("products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product output = productService.createProduct(product);
        if(output == null) return null;
        return  from(output);
    }

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id,@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product output = productService.replaceProduct(product,id);
        if(output == null) return null;
        return  from(output);
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
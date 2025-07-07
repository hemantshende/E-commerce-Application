package com.productCatalougeService.productCatalougeService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ProductController {
    @GetMapping("/hello")
    public String helloworld(){
        return "Hello World...!!!123456678";
    }
}

package com.example.apirest.controllers;

import com.example.apirest.entities.ProductImage;
import com.example.apirest.services.ProductImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/product-images")
public class ProductImageController extends BaseController<ProductImage, Integer> {

    @Autowired
    private ProductImageServiceImpl productImageServiceImpl;

    public ProductImageController(ProductImageServiceImpl productImageServiceImpl) {
        super(productImageServiceImpl);
    }
} 
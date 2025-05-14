package com.example.apirest.controllers;

import com.example.apirest.entities.Product;
import com.example.apirest.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/products")
public class ProductController extends BaseController<Product, Integer> {

    @Autowired
    private ProductServiceImp productServiceImp;

    public ProductController(ProductServiceImp productServiceImp) {
        super(productServiceImp);
    }
}   
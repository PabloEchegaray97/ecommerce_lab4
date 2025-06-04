package com.example.apirest.controllers;

import com.example.apirest.entities.Brand;
import com.example.apirest.services.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/brands")
public class BrandController extends BaseController<Brand, Integer> {

    @Autowired
    private BrandServiceImpl brandServiceImpl;

    public BrandController(BrandServiceImpl brandServiceImpl) {
        super(brandServiceImpl);
    }

}
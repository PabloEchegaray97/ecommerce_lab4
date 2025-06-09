package com.example.apirest.controllers;

import com.example.apirest.entities.Category;
import com.example.apirest.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/categories")
public class CategoryController extends BaseController<Category, Integer> {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        super(categoryServiceImpl);
    }
}
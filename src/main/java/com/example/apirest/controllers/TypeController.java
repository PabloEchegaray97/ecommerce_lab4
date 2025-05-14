package com.example.apirest.controllers;

import com.example.apirest.entities.Type;
import com.example.apirest.services.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/types")
public class TypeController extends BaseController<Type, Integer> {

    @Autowired
    private TypeServiceImpl typeServiceImpl;

    public TypeController(TypeServiceImpl typeServiceImpl) {
        super(typeServiceImpl);
    }

}
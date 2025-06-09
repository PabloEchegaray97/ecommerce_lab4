package com.example.apirest.controllers;

import com.example.apirest.entities.Size;
import com.example.apirest.services.SizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/sizes")
public class SizeController extends BaseController<Size, Integer> {

    @Autowired
    private SizeServiceImpl sizeServiceImpl;

    public SizeController(SizeServiceImpl sizeServiceImpl) {
        super(sizeServiceImpl);
    }
}
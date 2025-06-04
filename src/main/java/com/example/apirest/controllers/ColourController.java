package com.example.apirest.controllers;

import com.example.apirest.entities.Colour;
import com.example.apirest.services.ColourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/colours")
public class ColourController extends BaseController<Colour, Integer> {

    @Autowired
    private ColourServiceImpl colourServiceImpl;

    public ColourController(ColourServiceImpl colourServiceImpl) {
        super(colourServiceImpl);
    }

}
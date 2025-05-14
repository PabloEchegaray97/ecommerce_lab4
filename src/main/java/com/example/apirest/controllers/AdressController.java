package com.example.apirest.controllers;

import com.example.apirest.entities.Adress;
import com.example.apirest.services.AdressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/addresses")
public class AdressController extends BaseController<Adress, Integer> {

    @Autowired
    private AdressServiceImpl adressServiceImpl;

    public AdressController(AdressServiceImpl adressServiceImpl) {
        super(adressServiceImpl);
    }
}
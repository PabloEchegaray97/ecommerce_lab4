package com.example.apirest.controllers;

import com.example.apirest.entities.Detail;
import com.example.apirest.services.DetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/details")
public class DetailController extends BaseController<Detail, Integer> {

    @Autowired
    private DetailServiceImpl detailServiceImpl;

    public DetailController(DetailServiceImpl detailServiceImpl) {
        super(detailServiceImpl);
    }

 }
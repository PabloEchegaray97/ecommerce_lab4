package com.example.apirest.controllers;

import com.example.apirest.entities.Discount;
import com.example.apirest.services.DiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/discounts")
public class DiscountController extends BaseController<Discount, Integer> {

    @Autowired
    private DiscountServiceImpl discountServiceImpl;

    public DiscountController(DiscountServiceImpl discountServiceImpl) {
        super(discountServiceImpl);
    }

}
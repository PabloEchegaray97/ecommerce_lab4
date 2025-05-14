package com.example.apirest.services;

import com.example.apirest.entities.Discount;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl extends BaseServiceImpl<Discount, Integer> {
    
    @Autowired
    private DiscountRepository discountRepository;
    
    public DiscountServiceImpl(BaseRepository<Discount, Integer> baseRepository) {
        super(baseRepository);
    }
}
package com.example.apirest.services;

import com.example.apirest.entities.Brand;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends BaseServiceImpl<Brand, Integer> {
    
    @Autowired
    private BrandRepository brandRepository;
    
    public BrandServiceImpl(BaseRepository<Brand, Integer> baseRepository) {
        super(baseRepository);
    }
} 
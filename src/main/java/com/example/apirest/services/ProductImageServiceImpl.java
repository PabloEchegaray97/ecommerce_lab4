package com.example.apirest.services;

import com.example.apirest.entities.ProductImage;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImpl extends BaseServiceImpl<ProductImage, Integer> {
    
    @Autowired
    private ProductImageRepository productImageRepository;
    
    public ProductImageServiceImpl(BaseRepository<ProductImage, Integer> baseRepository) {
        super(baseRepository);
    }
    
} 
package com.example.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apirest.entities.Product;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.ProductRepository;

@Service
public class ProductServiceImp extends BaseServiceImpl<Product, Integer>{

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImp(BaseRepository<Product, Integer> baseRepository) {
        super(baseRepository);
    }

    
}

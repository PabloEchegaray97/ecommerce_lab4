package com.example.apirest.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apirest.entities.Product;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.ProductRepository;

@Service
public class ProductServiceImp extends BaseServiceImpl<Product, Integer> implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImp(BaseRepository<Product, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Page<Product> search(String filtro, Pageable pageable) throws Exception {
        try {
            return productRepository.search(filtro, pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

package com.example.apirest.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.apirest.entities.Product;

public interface ProductService extends BaseService<Product, Integer> {
    Page<Product> search(String filtro, Pageable pageable) throws Exception;
}

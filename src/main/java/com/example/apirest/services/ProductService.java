package com.example.apirest.services;

import java.util.List;

import com.example.apirest.entities.Product;

public interface ProductService extends BaseService<Product, Integer> {
    List<Product> search(String filtro) throws Exception;
}

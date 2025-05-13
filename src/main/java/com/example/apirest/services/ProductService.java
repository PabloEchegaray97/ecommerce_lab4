package com.example.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.apirest.entities.Product;
import com.example.apirest.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService implements BaseService<Product>{

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<Product> findAll() throws Exception {
        try {
            List<Product> entities = productRepository.findAll();
            if (entities.isEmpty()) {
                throw new Exception("No se encontraron productos");
            }
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Product findById(Integer id) throws Exception {
        try {
            Optional<Product> entityOptional = productRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el producto con id: " + id);
            }
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Product save(Product entity) throws Exception {
        try {
            return productRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Product update(Integer id, Product entity) throws Exception {
        try {
            Optional<Product> entityOptional = productRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el producto con id: " + id);
            }
            Product product = entityOptional.get();
            BeanUtils.copyProperties(entity, product, "id");
            return productRepository.save(product);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Integer id) throws Exception {
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No se encontró el producto con id: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

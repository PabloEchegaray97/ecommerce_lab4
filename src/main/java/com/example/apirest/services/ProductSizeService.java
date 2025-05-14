package com.example.apirest.services;

import com.example.apirest.entities.ProductSize;
import com.example.apirest.entities.ProductSizeId;
import com.example.apirest.repositories.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSizeService {
    
    @Autowired
    private ProductSizeRepository productSizeRepository;
    
    @Transactional
    public List<ProductSize> findAll() throws Exception {
        try {
            return productSizeRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public ProductSize findById(ProductSizeId id) throws Exception {
        try {
            Optional<ProductSize> entityOptional = productSizeRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public ProductSize save(ProductSize entity) throws Exception {
        try {
            entity = productSizeRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public ProductSize update(ProductSizeId id, ProductSize entity) throws Exception {
        try {
            Optional<ProductSize> entityOptional = productSizeRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el registro con id: " + id);
            }
            ProductSize productSize = entityOptional.get();
            BeanUtils.copyProperties(entity, productSize, "idSize", "idProduct");
            return productSize;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public boolean delete(ProductSizeId id) throws Exception {
        try {
            if (productSizeRepository.existsById(id)) {
                productSizeRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No existe el registro con id: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
} 
package com.example.apirest.services;

import com.example.apirest.entities.ProductDiscount;
import com.example.apirest.entities.ProductDiscountId;
import com.example.apirest.repositories.ProductDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDiscountService {
    
    @Autowired
    private ProductDiscountRepository productDiscountRepository;
    
    @Transactional
    public List<ProductDiscount> findAll() throws Exception {
        try {
            return productDiscountRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public ProductDiscount findById(ProductDiscountId id) throws Exception {
        try {
            Optional<ProductDiscount> entityOptional = productDiscountRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public ProductDiscount save(ProductDiscount entity) throws Exception {
        try {
            entity = productDiscountRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public ProductDiscount update(ProductDiscountId id, ProductDiscount entity) throws Exception {
        try {
            Optional<ProductDiscount> entityOptional = productDiscountRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el registro con id: " + id);
            }
            ProductDiscount productDiscount = entityOptional.get();
            // En este caso no copiamos idDiscount e idProduct ya que son las claves primarias
            // y no hay otros campos para actualizar
            // Si se agregan campos adicionales en el futuro, se pueden copiar aquí
            return productDiscount;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public boolean delete(ProductDiscountId id) throws Exception {
        try {
            if (productDiscountRepository.existsById(id)) {
                productDiscountRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No existe el registro con id: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
} 
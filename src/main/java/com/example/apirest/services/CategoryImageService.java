package com.example.apirest.services;

import com.example.apirest.dto.CategoryImageDTO;
import com.example.apirest.dto.CreateCategoryImageDTO;
import com.example.apirest.entities.CategoryImage;

import java.util.List;

public interface CategoryImageService extends BaseService<CategoryImage, Integer> {
    
    // Métodos específicos para CategoryImage
    List<CategoryImageDTO> findAllActiveOrderByPosition() throws Exception;
    List<CategoryImageDTO> findAllOrderedNotDeleted() throws Exception;
    List<CategoryImageDTO> findByProductId(Integer productId) throws Exception;
    List<CategoryImageDTO> findAllByProductId(Integer productId) throws Exception;
    CategoryImageDTO createCategoryImage(CreateCategoryImageDTO dto) throws Exception;
    CategoryImageDTO updatePosition(Integer id, Integer newPosition) throws Exception;
} 
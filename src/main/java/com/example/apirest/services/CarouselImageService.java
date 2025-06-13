package com.example.apirest.services;

import com.example.apirest.entities.CarouselImage;

import java.util.List;

public interface CarouselImageService extends BaseService<CarouselImage, Integer> {
    
    // Métodos específicos para CarouselImage
    List<CarouselImage> findAllActiveOrderByPosition() throws Exception;
    List<CarouselImage> findByProductId(Integer productId) throws Exception;
    List<CarouselImage> findAllCatalogLinks() throws Exception;
    CarouselImage updatePosition(Integer id, Integer newPosition) throws Exception;
} 
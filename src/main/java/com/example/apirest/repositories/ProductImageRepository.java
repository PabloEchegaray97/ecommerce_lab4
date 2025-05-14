package com.example.apirest.repositories;

import com.example.apirest.entities.ProductImage;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends BaseRepository<ProductImage, Integer> {
} 
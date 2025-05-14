package com.example.apirest.repositories;

import com.example.apirest.entities.ProductSize;
import com.example.apirest.entities.ProductSizeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeId> {
} 
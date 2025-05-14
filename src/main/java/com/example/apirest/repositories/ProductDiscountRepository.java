package com.example.apirest.repositories;

import com.example.apirest.entities.ProductDiscount;
import com.example.apirest.entities.ProductDiscountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, ProductDiscountId> {
} 
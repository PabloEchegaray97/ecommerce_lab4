package com.example.apirest.repositories;

import com.example.apirest.entities.ProductDiscount;
import com.example.apirest.entities.ProductDiscountId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "productDiscounts")
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, ProductDiscountId> {
    
    @RestResource(path = "byProduct")
    List<ProductDiscount> findByIdProduct(@Param("idProduct") Integer idProduct);
    
    @RestResource(path = "byDiscount")
    List<ProductDiscount> findByIdDiscount(@Param("idDiscount") Integer idDiscount);
} 
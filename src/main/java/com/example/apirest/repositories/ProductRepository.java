package com.example.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.apirest.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}

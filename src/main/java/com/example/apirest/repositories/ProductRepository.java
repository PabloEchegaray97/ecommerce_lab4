package com.example.apirest.repositories;

import com.example.apirest.entities.Product;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "products")
public interface ProductRepository extends BaseRepository<Product, Integer> {
}

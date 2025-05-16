package com.example.apirest.repositories;

import com.example.apirest.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "products")
public interface ProductRepository extends BaseRepository<Product, Integer> {

    // Busca productos por nombre o descripcion - ej.
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:filtro% OR p.description LIKE %:filtro%")
    List<Product> search(@Param("filtro") String filtro);
    
}

package com.example.apirest.repositories;

import com.example.apirest.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(path = "products")
public interface ProductRepository extends BaseRepository<Product, Integer> {

    // Busca productos por nombre o descripcion - ej.
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:filtro% OR p.description LIKE %:filtro%")
    Page<Product> search(@Param("filtro") String filtro, Pageable pageable);
    
    // Obtiene todos los productos con sus talles y stock
    @Query("SELECT DISTINCT p FROM Product p " +
           "LEFT JOIN FETCH p.availableSizes ps " +
           "LEFT JOIN FETCH ps.size s " +
           "WHERE p.status = true")
    List<Product> findAllWithSizes();
    
    // Obtiene un producto específico con sus talles y stock
    @Query("SELECT p FROM Product p " +
           "LEFT JOIN FETCH p.availableSizes ps " +
           "LEFT JOIN FETCH ps.size s " +
           "WHERE p.id = :id AND p.status = true")
    Optional<Product> findByIdWithSizes(@Param("id") Integer id);
    
}

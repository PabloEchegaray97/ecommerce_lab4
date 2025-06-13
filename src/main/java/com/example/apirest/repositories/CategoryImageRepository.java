package com.example.apirest.repositories;

import com.example.apirest.entities.CategoryImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryImageRepository extends BaseRepository<CategoryImage, Integer> {
    
    // Consulta para obtener imágenes de categorías activas ordenadas por posición
    @Query("SELECT c FROM CategoryImage c WHERE c.isActive = true AND c.deletedAt IS NULL ORDER BY c.position ASC")
    List<CategoryImage> findAllActiveOrderByPosition();
    
    // Consulta para obtener todas las imágenes no eliminadas ordenadas por posición (para admin)
    @Query("SELECT c FROM CategoryImage c WHERE c.deletedAt IS NULL ORDER BY c.position ASC")
    List<CategoryImage> findAllOrderedNotDeleted();
    
    // Consulta para obtener imágenes por producto
    @Query("SELECT c FROM CategoryImage c WHERE c.productId = :productId AND c.isActive = true AND c.deletedAt IS NULL ORDER BY c.position ASC")
    List<CategoryImage> findByProductId(@Param("productId") Integer productId);
    
    // Consulta para obtener todas las imágenes por producto (incluyendo inactivas, para admin)
    @Query("SELECT c FROM CategoryImage c WHERE c.productId = :productId AND c.deletedAt IS NULL ORDER BY c.position ASC")
    List<CategoryImage> findAllByProductId(@Param("productId") Integer productId);
} 
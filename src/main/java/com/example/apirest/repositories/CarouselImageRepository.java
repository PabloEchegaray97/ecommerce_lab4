package com.example.apirest.repositories;

import com.example.apirest.entities.CarouselImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarouselImageRepository extends BaseRepository<CarouselImage, Integer> {
    
    // Consulta para obtener imágenes del carousel ordenadas por posición
    @Query("SELECT c FROM CarouselImage c WHERE c.isActive = true AND c.deletedAt IS NULL ORDER BY c.position ASC")
    List<CarouselImage> findAllActiveOrderByPosition();
    
    // Consulta para obtener imágenes por producto
    @Query("SELECT c FROM CarouselImage c WHERE c.productId = :productId AND c.isActive = true AND c.deletedAt IS NULL")
    List<CarouselImage> findByProductId(@Param("productId") Integer productId);
    
    // Consulta para obtener imágenes que son enlaces al catálogo
    @Query("SELECT c FROM CarouselImage c WHERE c.isCatalogLink = true AND c.isActive = true AND c.deletedAt IS NULL ORDER BY c.position ASC")
    List<CarouselImage> findAllCatalogLinks();
} 
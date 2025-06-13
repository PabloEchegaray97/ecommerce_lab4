package com.example.apirest.controllers;

import com.example.apirest.entities.CarouselImage;
import com.example.apirest.services.CarouselImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/carousel-images")
public class CarouselImageController extends BaseController<CarouselImage, Integer> {

    @Autowired
    private CarouselImageServiceImpl carouselImageServiceImpl;

    public CarouselImageController(CarouselImageServiceImpl carouselImageServiceImpl) {
        super(carouselImageServiceImpl);
    }
    
    // Endpoint para obtener todas las imágenes activas ordenadas por posición
    @GetMapping("/ordered")
    public ResponseEntity<?> getAllActiveOrderByPosition() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carouselImageServiceImpl.findAllActiveOrderByPosition());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    // Endpoint para obtener imágenes por producto
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getByProductId(@PathVariable Integer productId) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carouselImageServiceImpl.findByProductId(productId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    // Endpoint para obtener enlaces al catálogo
    @GetMapping("/catalog-links")
    public ResponseEntity<?> getAllCatalogLinks() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carouselImageServiceImpl.findAllCatalogLinks());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    // Endpoint para actualizar la posición de una imagen
    @PutMapping("/{id}/position")
    public ResponseEntity<?> updatePosition(@PathVariable Integer id, @RequestBody PositionUpdateRequest request) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carouselImageServiceImpl.updatePosition(id, request.getPosition()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    // Clase interna para el request de actualización de posición
    public static class PositionUpdateRequest {
        private Integer position;
        
        public Integer getPosition() {
            return position;
        }
        
        public void setPosition(Integer position) {
            this.position = position;
        }
    }
} 
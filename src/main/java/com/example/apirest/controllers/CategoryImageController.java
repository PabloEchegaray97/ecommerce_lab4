package com.example.apirest.controllers;

import com.example.apirest.dto.CategoryImageDTO;
import com.example.apirest.dto.CreateCategoryImageDTO;
import com.example.apirest.entities.CategoryImage;
import com.example.apirest.services.CategoryImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/category-images")
public class CategoryImageController extends BaseController<CategoryImage, Integer> {

    @Autowired
    private CategoryImageServiceImpl categoryImageService;

    public CategoryImageController(CategoryImageServiceImpl categoryImageService) {
        super(categoryImageService);
    }

    // Sobrescribir el método save() heredado para usar nuestro DTO personalizado
    @Override
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody CategoryImage entity) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryImageService.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Sobrescribir el método activate() heredado
    @Override
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryImageService.activate(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Sobrescribir el método deactivate() heredado
    @Override
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryImageService.deactivate(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // GET /api/v1/category-images/ordered - Obtener imágenes activas ordenadas por posición
    @GetMapping("/ordered")
    public ResponseEntity<?> getOrderedCategoryImages() {
        try {
            List<CategoryImageDTO> images = categoryImageService.findAllActiveOrderByPosition();
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al obtener las imágenes de categoría: " + e.getMessage() + "\"}");
        }
    }

    // GET /api/v1/category-images/all-ordered - Obtener todas las imágenes (incluidas inactivas) para admin
    @GetMapping("/all-ordered")
    public ResponseEntity<?> getAllOrderedCategoryImages() {
        try {
            List<CategoryImageDTO> images = categoryImageService.findAllOrderedNotDeleted();
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al obtener todas las imágenes de categoría: " + e.getMessage() + "\"}");
        }
    }

    // GET /api/v1/category-images/product/{productId} - Obtener imágenes activas por producto
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getCategoryImagesByProduct(@PathVariable Integer productId) {
        try {
            List<CategoryImageDTO> images = categoryImageService.findByProductId(productId);
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al obtener las imágenes de categoría por producto: " + e.getMessage() + "\"}");
        }
    }

    // GET /api/v1/category-images/product/{productId}/all - Obtener todas las imágenes por producto (admin)
    @GetMapping("/product/{productId}/all")
    public ResponseEntity<?> getAllCategoryImagesByProduct(@PathVariable Integer productId) {
        try {
            List<CategoryImageDTO> images = categoryImageService.findAllByProductId(productId);
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al obtener todas las imágenes de categoría por producto: " + e.getMessage() + "\"}");
        }
    }

    // POST /api/v1/category-images/create - Crear nueva imagen de categoría con DTO
    @PostMapping("/create")
    public ResponseEntity<?> createCategoryImage(@Valid @RequestBody CreateCategoryImageDTO dto) {
        try {
            CategoryImageDTO createdImage = categoryImageService.createCategoryImage(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error al crear la imagen de categoría: " + e.getMessage() + "\"}");
        }
    }

    // PUT /api/v1/category-images/{id}/position - Actualizar posición de la imagen
    @PutMapping("/{id}/position")
    public ResponseEntity<?> updatePosition(@PathVariable Integer id, @RequestBody Integer newPosition) {
        try {
            CategoryImageDTO updatedImage = categoryImageService.updatePosition(id, newPosition);
            return ResponseEntity.ok(updatedImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error al actualizar la posición: " + e.getMessage() + "\"}");
        }
    }
} 
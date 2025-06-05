package com.example.apirest.controllers;

import com.example.apirest.entities.Brand;
import com.example.apirest.services.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/brands")
public class BrandController extends BaseController<Brand, Integer> {

    @Autowired
    private BrandServiceImpl brandServiceImpl;

    public BrandController(BrandServiceImpl brandServiceImpl) {
        super(brandServiceImpl);
    }

    // ENDPOINT BULK PARA MARCAS (ENFOQUE EN VANS)
    @PostMapping("/bulk")
    public ResponseEntity<?> createBulkBrands() {
        try {
            List<Brand> brands = new ArrayList<>();
            
            
            String[] brandNames = {
                "Vans", "Vans Knu", "Vans Old Skoll", "Vans Training", "Vans Outdoor", 
                "Vans Autenthic", "Vans Slip-On", "Vans Work", "Vans Life", "Vans Shoes"
            };

            for (String brandName : brandNames) {
                Brand brand = new Brand();
                brand.setName(brandName);
                brands.add(brand);
            }

            List<Brand> savedBrands = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (Brand brand : brands) {
                try {
                    Brand saved = brandServiceImpl.save(brand);
                    savedBrands.add(saved);
                } catch (Exception e) {
                    errors.add("Error al guardar marca " + brand.getName() + ": " + e.getMessage());
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("created", savedBrands.size());
            response.put("total", brands.size());
            response.put("brands", savedBrands);
            if (!errors.isEmpty()) {
                response.put("errors", errors);
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Error en carga masiva: " + e.getMessage()));
        }
    }
}
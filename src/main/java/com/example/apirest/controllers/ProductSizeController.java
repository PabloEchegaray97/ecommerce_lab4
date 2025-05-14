package com.example.apirest.controllers;

import com.example.apirest.entities.ProductSize;
import com.example.apirest.entities.ProductSizeId;
import com.example.apirest.services.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/product-sizes")
public class ProductSizeController {

    @Autowired
    private ProductSizeService productSizeService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productSizeService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{idSize}/{idProduct}")
    public ResponseEntity<?> getOne(@PathVariable Integer idSize, @PathVariable Integer idProduct) {
        try {
            ProductSizeId id = new ProductSizeId(idSize, idProduct);
            return ResponseEntity.status(HttpStatus.OK).body(productSizeService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ProductSize entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productSizeService.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{idSize}/{idProduct}")
    public ResponseEntity<?> update(@PathVariable Integer idSize, @PathVariable Integer idProduct, @RequestBody ProductSize entity) {
        try {
            ProductSizeId id = new ProductSizeId(idSize, idProduct);
            return ResponseEntity.status(HttpStatus.OK).body(productSizeService.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/{idSize}/{idProduct}")
    public ResponseEntity<?> delete(@PathVariable Integer idSize, @PathVariable Integer idProduct) {
        try {
            ProductSizeId id = new ProductSizeId(idSize, idProduct);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productSizeService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
} 
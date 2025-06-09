package com.example.apirest.controllers;

import com.example.apirest.entities.Product;
import com.example.apirest.services.ProductServiceImp;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/products")
public class ProductController extends BaseController<Product, Integer> {

    @Autowired
    private ProductServiceImp productServiceImp;

    public ProductController(ProductServiceImp productServiceImp) {
        super(productServiceImp);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productServiceImp.search(filtro, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/with-sizes")
    public ResponseEntity<?> getProductsWithSizes() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productServiceImp.findAllWithSizes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}/with-sizes")
    public ResponseEntity<?> getProductWithSizes(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productServiceImp.findByIdWithSizes(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
package com.example.apirest.controllers;

import com.example.apirest.entities.ProductDiscount;
import com.example.apirest.entities.ProductDiscountId;
import com.example.apirest.services.ProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/product-discounts")
public class ProductDiscountController {

    @Autowired
    private ProductDiscountService productDiscountService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productDiscountService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{idDiscount}/{idProduct}")
    public ResponseEntity<?> getOne(@PathVariable Integer idDiscount, @PathVariable Integer idProduct) {
        try {
            ProductDiscountId id = new ProductDiscountId(idDiscount, idProduct);
            return ResponseEntity.status(HttpStatus.OK).body(productDiscountService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ProductDiscount entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productDiscountService.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{idDiscount}/{idProduct}")
    public ResponseEntity<?> update(@PathVariable Integer idDiscount, @PathVariable Integer idProduct, @RequestBody ProductDiscount entity) {
        try {
            ProductDiscountId id = new ProductDiscountId(idDiscount, idProduct);
            return ResponseEntity.status(HttpStatus.OK).body(productDiscountService.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/{idDiscount}/{idProduct}")
    public ResponseEntity<?> delete(@PathVariable Integer idDiscount, @PathVariable Integer idProduct) {
        try {
            ProductDiscountId id = new ProductDiscountId(idDiscount, idProduct);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productDiscountService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/byProduct/{idProduct}")
    public ResponseEntity<?> getByProduct(@PathVariable Integer idProduct) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productDiscountService.findByIdProduct(idProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
} 
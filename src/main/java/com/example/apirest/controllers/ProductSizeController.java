package com.example.apirest.controllers;

import com.example.apirest.entities.ProductSize;
import com.example.apirest.entities.ProductSizeId;
import com.example.apirest.services.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
    
    // Nuevo endpoint: Obtener stock por producto
    @GetMapping("/product/{productId}/stock")
    public ResponseEntity<?> getStockByProduct(@PathVariable Integer productId) {
        try {
            List<ProductSize> productSizes = productSizeService.findAll();
            List<ProductSize> productStock = productSizes.stream()
                .filter(ps -> ps.getIdProduct().equals(productId))
                .toList();
            
            if (productStock.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"No se encontró stock para el producto con ID: " + productId + "\"}");
            }
            
            return ResponseEntity.status(HttpStatus.OK).body(productStock);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    // Nuevo endpoint: Actualizar stock específico
    @PutMapping("/{idSize}/{idProduct}/stock")
    public ResponseEntity<?> updateStock(@PathVariable Integer idSize, @PathVariable Integer idProduct, @RequestBody Map<String, Integer> stockData) {
        try {
            ProductSizeId id = new ProductSizeId(idSize, idProduct);
            ProductSize productSize = productSizeService.findById(id);
            
            if (stockData.containsKey("stock")) {
                Integer newStock = stockData.get("stock");
                if (newStock < 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\":\"El stock no puede ser negativo\"}");
                }
                productSize.setStock(newStock);
                ProductSize updated = productSizeService.update(id, productSize);
                return ResponseEntity.status(HttpStatus.OK).body(updated);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Se requiere el campo 'stock'\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    // Nuevo endpoint: Obtener productos con stock disponible (stock > 0)
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableStock() {
        try {
            List<ProductSize> allProductSizes = productSizeService.findAll();
            List<ProductSize> availableStock = allProductSizes.stream()
                .filter(ps -> ps.getStock() != null && ps.getStock() > 0)
                .toList();
            
            return ResponseEntity.status(HttpStatus.OK).body(availableStock);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    // Nuevo endpoint: Verificar disponibilidad de stock específico
    @GetMapping("/{idSize}/{idProduct}/check-stock/{quantity}")
    public ResponseEntity<?> checkStock(@PathVariable Integer idSize, @PathVariable Integer idProduct, @PathVariable Integer quantity) {
        try {
            ProductSizeId id = new ProductSizeId(idSize, idProduct);
            ProductSize productSize = productSizeService.findById(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("productId", idProduct);
            response.put("sizeId", idSize);
            response.put("requestedQuantity", quantity);
            response.put("availableStock", productSize.getStock());
            response.put("isAvailable", productSize.getStock() >= quantity);
            
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/update-multiple-stock")
    public ResponseEntity<?> updateMultipleStock(@RequestBody List<Map<String, Object>> stockUpdates) {
        try {
            for (Map<String, Object> update : stockUpdates) {
                Integer idProduct = (Integer) update.get("idProduct");
                Integer idSize = (Integer) update.get("idSize");
                Integer stock = (Integer) update.get("stock");
                if (idProduct == null || idSize == null || stock == null) continue;
                ProductSizeId psId = new ProductSizeId(idSize, idProduct);
                ProductSize ps = productSizeService.findById(psId);
                if (ps != null) {
                    ps.setStock(stock);
                    productSizeService.update(psId, ps);
                }
            }
            return ResponseEntity.ok("{\"message\":\"Stock actualizado correctamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
} 
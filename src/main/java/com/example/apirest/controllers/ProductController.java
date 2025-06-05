package com.example.apirest.controllers;

import com.example.apirest.entities.Product;
import com.example.apirest.services.ProductServiceImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // ENDPOINT BULK PARA PRODUCTOS (ENFOQUE EN VANS)
    @PostMapping("/bulk")
    public ResponseEntity<?> createBulkProducts() {
        try {
            List<Product> products = new ArrayList<>();

            // productos Vans populares (asumiendo IDs: Vans=1, colores varios, categorías
            // varias)
            String[][] productData = {
                    { "Vans Old Skool Black/White", "89.99", "Clásicas zapatillas Vans Old Skool en negro y blanco",
                            "https://images.vans.com/is/image/Vans/VN000D3HY28-HERO", "5", "1", "1" },
                    { "Vans Authentic Red", "65.99", "Zapatillas Vans Authentic rojas clásicas",
                            "https://images.vans.com/is/image/Vans/VN000EE3RED-HERO", "2", "3", "1" },
                    { "Vans Sk8-Hi Black", "79.99", "Zapatillas altas Vans Sk8-Hi negras",
                            "https://images.vans.com/is/image/Vans/VN000D5IB8C-HERO", "5", "1", "1" },
                    { "Vans Era Navy", "69.99", "Zapatillas Vans Era azul marino",
                            "https://images.vans.com/is/image/Vans/VN000EWZNVY-HERO", "2", "4", "1" },
                    { "Vans Slip-On Checkerboard", "59.99", "Vans Slip-On con patrón de tablero de ajedrez",
                            "https://images.vans.com/is/image/Vans/VN000EYEBWW-HERO", "7", "1", "1" },
                    { "Vans Old Skool White", "89.99", "Vans Old Skool completamente blancas",
                            "https://images.vans.com/is/image/Vans/VN000D3HW00-HERO", "5", "2", "1" },
                    { "Vans Authentic Pink", "65.99", "Zapatillas Vans Authentic rosas",
                            "https://images.vans.com/is/image/Vans/VN000EE3PNK-HERO", "2", "7", "1" },
                    { "Vans Sk8-Hi Pro", "89.99", "Vans Sk8-Hi Pro para skateboarding profesional",
                            "https://images.vans.com/is/image/Vans/VN000VHQBKA-HERO", "5", "1", "1" }
            };

            for (String[] prodData : productData) {
                Product product = new Product();
                product.setName(prodData[0]);
                product.setPrice(Double.parseDouble(prodData[1]));
                product.setDescription(prodData[2]);
                product.setImage(prodData[3]);
                product.setCategoryId(Integer.parseInt(prodData[4]));
                product.setColourId(Integer.parseInt(prodData[5]));
                product.setBrandId(Integer.parseInt(prodData[6]));
                product.setStatus(true);
                products.add(product);
            }

            List<Product> savedProducts = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (Product product : products) {
                try {
                    Product saved = productServiceImp.save(product);
                    savedProducts.add(saved);
                } catch (Exception e) {
                    errors.add("Error al guardar producto " + product.getName() + ": " + e.getMessage());
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("created", savedProducts.size());
            response.put("total", products.size());
            response.put("products", savedProducts);
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
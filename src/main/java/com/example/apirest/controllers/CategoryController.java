package com.example.apirest.controllers;

import com.example.apirest.entities.Category;
import com.example.apirest.services.CategoryServiceImpl;
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
@RequestMapping(path = "api/v1/categories")
public class CategoryController extends BaseController<Category, Integer> {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        super(categoryServiceImpl);
    }

    // ENDPOINT BULK PARA CATEGORÍAS
    @PostMapping("/bulk")
    public ResponseEntity<?> createBulkCategories() {
        try {
            List<Category> categories = new ArrayList<>();
            
            // categorias basadas en los tipos (asumiendo que ya existen tipos con IDs 1-8)
            String[][] categoryData = {
                {"Zapatillas Deportivas", "1"},
                {"Zapatillas Casuales", "2"},
                {"Zapatillas Running", "3"},
                {"Zapatillas Basketball", "4"},
                {"Zapatillas Skate", "5"},
                {"Zapatillas Training", "6"},
                {"Zapatillas Lifestyle", "7"},
                {"Zapatillas Outdoor", "8"}
            };

            for (String[] catData : categoryData) {
                Category category = new Category();
                category.setName(catData[0]);
                category.setTypeId(Integer.parseInt(catData[1]));
                categories.add(category);
            }

            List<Category> savedCategories = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (Category category : categories) {
                try {
                    Category saved = categoryServiceImpl.save(category);
                    savedCategories.add(saved);
                } catch (Exception e) {
                    errors.add("Error al guardar categoría " + category.getName() + ": " + e.getMessage());
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("created", savedCategories.size());
            response.put("total", categories.size());
            response.put("categories", savedCategories);
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
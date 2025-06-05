package com.example.apirest.controllers;

import com.example.apirest.entities.Type;
import com.example.apirest.services.TypeServiceImpl;
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
@RequestMapping(path = "api/v1/types")
public class TypeController extends BaseController<Type, Integer> {

    @Autowired
    private TypeServiceImpl typeServiceImpl;

    public TypeController(TypeServiceImpl typeServiceImpl) {
        super(typeServiceImpl);
    }

    // ENDPOINT BULK PARA TIPOS
    @PostMapping("/bulk")
    public ResponseEntity<?> createBulkTypes() {
        try {
            List<Type> types = new ArrayList<>();
            
            // tipos de zapatillas
            String[] typeNames = {
                "Deportiva", "Casual", "Running", "Basketball", 
                "Skateboarding", "Training", "Lifestyle", "Outdoor"
            };

            for (String typeName : typeNames) {
                Type type = new Type();
                type.setName(typeName);
                types.add(type);
            }

            List<Type> savedTypes = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (Type type : types) {
                try {
                    Type saved = typeServiceImpl.save(type);
                    savedTypes.add(saved);
                } catch (Exception e) {
                    errors.add("Error al guardar tipo " + type.getName() + ": " + e.getMessage());
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("created", savedTypes.size());
            response.put("total", types.size());
            response.put("types", savedTypes);
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
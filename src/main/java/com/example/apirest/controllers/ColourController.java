package com.example.apirest.controllers;

import com.example.apirest.entities.Colour;
import com.example.apirest.services.ColourServiceImpl;
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
@RequestMapping(path = "api/v1/colours")
public class ColourController extends BaseController<Colour, Integer> {

    @Autowired
    private ColourServiceImpl colourServiceImpl;

    public ColourController(ColourServiceImpl colourServiceImpl) {
        super(colourServiceImpl);
    }

    // ENDPOINT BULK PARA COLORES
    @PostMapping("/bulk")
    public ResponseEntity<?> createBulkColours() {
        try {
            List<Colour> colours = new ArrayList<>();
            
            // colores basicos para zapatillas
            String[][] colorData = {
                {"Negro", "#000000"},
                {"Blanco", "#FFFFFF"},
                {"Rojo", "#FF0000"},
                {"Azul", "#0000FF"},
                {"Verde", "#00FF00"},
                {"Amarillo", "#FFFF00"},
                {"Rosa", "#FF69B4"},
                {"Naranja", "#FFA500"},
                {"Morado", "#800080"},
                {"Gris", "#808080"},
                {"Marrón", "#8B4513"},
                {"Beige", "#F5F5DC"},
                {"Turquesa", "#40E0D0"},
                {"Dorado", "#FFD700"},
                {"Plateado", "#C0C0C0"}
            };

            for (String[] colorInfo : colorData) {
                Colour colour = new Colour();
                colour.setName(colorInfo[0]);
                colour.setValue(colorInfo[1]);
                colours.add(colour);
            }

            List<Colour> savedColours = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (Colour colour : colours) {
                try {
                    Colour saved = colourServiceImpl.save(colour);
                    savedColours.add(saved);
                } catch (Exception e) {
                    errors.add("Error al guardar color " + colour.getName() + ": " + e.getMessage());
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("created", savedColours.size());
            response.put("total", colours.size());
            response.put("colours", savedColours);
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
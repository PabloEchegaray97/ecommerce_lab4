package com.example.apirest.controllers;

import com.example.apirest.entities.Size;
import com.example.apirest.services.SizeServiceImpl;
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
@RequestMapping(path = "api/v1/sizes")
public class SizeController extends BaseController<Size, Integer> {

    @Autowired
    private SizeServiceImpl sizeServiceImpl;

    public SizeController(SizeServiceImpl sizeServiceImpl) {
        super(sizeServiceImpl);
    }

    // ENDPOINT BULK PARA TALLES
    @PostMapping("/bulk")
    public ResponseEntity<?> createBulkSizes() {
        try {
            List<Size> sizes = new ArrayList<>();
            
            // talles EU (Europa) - mas comunes
            String[] euSizes = {"35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46"};
            for (String sizeNumber : euSizes) {
                Size size = new Size();
                size.setNumber(sizeNumber);
                size.setSystemType(Size.SystemType.EU);
                sizes.add(size);
            }

            // talles US (Estados Unidos) - populares
            String[] usSizes = {"6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "12"};
            for (String sizeNumber : usSizes) {
                Size size = new Size();
                size.setNumber(sizeNumber);
                size.setSystemType(Size.SystemType.US);
                sizes.add(size);
            }

            List<Size> savedSizes = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (Size size : sizes) {
                try {
                    Size saved = sizeServiceImpl.save(size);
                    savedSizes.add(saved);
                } catch (Exception e) {
                    errors.add("Error al guardar talle " + size.getNumber() + " " + size.getSystemType() + ": " + e.getMessage());
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("created", savedSizes.size());
            response.put("total", sizes.size());
            response.put("sizes", savedSizes);
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
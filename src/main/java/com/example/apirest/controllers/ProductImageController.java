package com.example.apirest.controllers;

import com.example.apirest.entities.ProductImage;
import com.example.apirest.services.ProductImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/product-images")
public class ProductImageController extends BaseController<ProductImage, Integer> {

    @Autowired
    private ProductImageServiceImpl productImageServiceImpl;

    // Directorio donde se guardarán las imágenes
    private static final String UPLOAD_DIR = "uploads/product-images/";

    public ProductImageController(ProductImageServiceImpl productImageServiceImpl) {
        super(productImageServiceImpl);
    }

    // NUEVO ENDPOINT PARA SUBIR ARCHIVOS
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") Integer productId,
            @RequestParam(value = "isPrincipal", defaultValue = "false") boolean isPrincipal) {
        
        try {
            // validar que el archivo no esté vacio
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("{\"error\":\"El archivo está vacío\"}");
            }

            // validar tipo de archivo
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body("{\"error\":\"Solo se permiten archivos de imagen\"}");
            }

            // crear directorio si no existe
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // generar nombre único para el archivo
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + extension;

            // guardar archivo en el servidor
            Path filePath = Paths.get(UPLOAD_DIR + uniqueFilename);
            Files.write(filePath, file.getBytes());

            // crear URL local para acceder a la imagen
            String imageUrl = "http://localhost:9000/api/v1/product-images/files/" + uniqueFilename;

            // crear y guardar ProductImage en la base de datos
            ProductImage productImage = new ProductImage();
            productImage.setLink(imageUrl);
            productImage.setProductId(productId);
            productImage.setPrincipalProductImage(isPrincipal);

            ProductImage savedImage = productImageServiceImpl.save(productImage);

            return ResponseEntity.ok(savedImage);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al guardar el archivo: " + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // ENDPOINT PARA SERVIR LAS Img
    @GetMapping("/files/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR + filename);
            
            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            byte[] imageBytes = Files.readAllBytes(filePath);
            
            // determinar el tipo de contenido basado en la extensión
            String contentType = "image/jpeg"; // por defecto
            if (filename.toLowerCase().endsWith(".png")) {
                contentType = "image/png";
            } else if (filename.toLowerCase().endsWith(".gif")) {
                contentType = "image/gif";
            }

            return ResponseEntity.ok()
                    .header("Content-Type", contentType)
                    .body(imageBytes);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
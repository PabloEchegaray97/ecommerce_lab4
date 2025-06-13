package com.example.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryImageDTO {
    
    private Integer id;
    private String imageUrl;
    private String productName;
    private Integer productId;
    private Integer position;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
    // Información del producto relacionado (opcional)
    private String productFullName;
} 
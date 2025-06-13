package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "category_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class CategoryImage extends Base {
    
    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;
    
    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;
    
    @Column(name = "product_id")
    private Integer productId;
    
    @Column(name = "position", nullable = false)
    private Integer position = 0;
    
    // Relación opcional con Product si quieres mantener integridad referencial
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
    
    // Constructor personalizado
    public CategoryImage(String imageUrl, String productName, Integer productId, Integer position) {
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.productId = productId;
        this.position = position;
    }
}

/*
{
    "imageUrl": "https://ejemplo.com/category-image.jpg",
    "productName": "Nike Air Max",
    "productId": 1,
    "position": 1
}
*/ 
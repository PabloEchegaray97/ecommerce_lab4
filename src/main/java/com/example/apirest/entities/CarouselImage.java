package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "carousel_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class CarouselImage extends Base {
    
    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;
    
    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;
    
    @Column(name = "product_id")
    private Integer productId;
    
    @Column(name = "is_catalog_link", nullable = false)
    private Boolean isCatalogLink = false;
    
    @Column(name = "position", nullable = false)
    private Integer position = 0;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;
    
    // Constructor personalizado
    public CarouselImage(String imageUrl, String productName, Integer productId, Boolean isCatalogLink, Integer position) {
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.productId = productId;
        this.isCatalogLink = isCatalogLink;
        this.position = position;
    }
}

/*
{
    "imageUrl": "https://ejemplo.com/carousel-image.jpg",
    "productName": "Nike Air Max",
    "productId": 1,
    "isCatalogLink": false,
    "position": 1
}
*/ 
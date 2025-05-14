package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "product_image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class ProductImage extends Base  {
    

    @Column(name = "type")
    private String type;
    
    @Column(name = "productId")
    private Integer productId;
}

/*
{
    "type": "https://ejemplo.com/nike.jpg",
    "productId": 1
}
*/ 
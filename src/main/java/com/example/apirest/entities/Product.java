package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.envers.Audited;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "colour")
    private short colour;

    @Column(name = "brand")
    private short brand;

    @Column(name = "status")
    private boolean status;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}


/*
{
    "name": "Zapatillas Nike Air Max",
    "price": 129.99,
    "description": "Zapatillas deportivas Nike Air Max con tecnología de amortiguación",
    "image": "https://ejemplo.com/nike-air-max.jpg",
    "categoryId": 1,
    "colour": 1,  // 1: Negro, 2: Blanco, 3: Rojo, etc.
    "brand": 1,   // 1: Nike, 2: Adidas, 3: Puma, etc.
    "status": true,
    "createdAt": "2024-03-20T10:00:00",
    "updatedAt": "2024-03-20T10:00:00"
}
*/

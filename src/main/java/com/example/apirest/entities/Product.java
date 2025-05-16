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
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Product extends Base  {
    
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

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;

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
    "status": true
}
*/

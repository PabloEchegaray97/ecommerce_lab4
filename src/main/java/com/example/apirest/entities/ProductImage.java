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
@Table(name = "product_image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class ProductImage extends Base  {
    

    @Column(name = "link")
    private String link;
    
    @Column(name = "product_id")
    private Integer productId;

    @Column(name= "is_principal_product_image") //por defecto es false, osea que si se agregan imagenes al producto solo se debe determinar cual es la principal, sino, dejar vacío
    private boolean isPrincipalProductImage;

    @ManyToOne
    @JoinColumn(name= "product_id", referencedColumnName = "id", insertable=false, updatable =false)
    private Product product;
}

/*
{
    "link": "https://ejemplo.com/nike.jpg",
    "productId": 3
}
*/ 
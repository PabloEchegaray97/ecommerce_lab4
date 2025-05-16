package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "product_size")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
@IdClass(ProductSizeId.class)
public class ProductSize implements Serializable {
    
    @Id
    @Column(name = "idSize")
    private Integer idSize;

    @Id
    @Column(name = "idProduct")
    private Integer idProduct;

    @ManyToOne
    @JoinColumn(name = "idSize", referencedColumnName = "id", insertable = false, updatable = false)
    private Size size;

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;
}

/*
{
    "idSize": 1,
    "idProduct": 1
}
*/ 
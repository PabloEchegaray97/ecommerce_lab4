package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "product_discount")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
@IdClass(ProductDiscountId.class)
public class ProductDiscount implements Serializable {
    
    @Id
    @Column(name = "idDiscount")
    private Integer idDiscount;

    @Id
    @Column(name = "idProduct")
    private Integer idProduct;
}

/*
{
    "idDiscount": 1,
    "idProduct": 1
}
*/ 
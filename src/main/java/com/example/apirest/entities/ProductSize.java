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
import java.time.LocalDateTime;

import org.hibernate.envers.Audited;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @Column(name = "id_size")
    private Integer idSize;

    @Id
    @Column(name = "id_product")
    private Integer idProduct;
    
    @Column(name = "stock")
    private Integer stock;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "id_size", referencedColumnName = "id", insertable = false, updatable = false)
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private Product product;
}

/*
{
    "idSize": 1,
    "idProduct": 1,
    "stock": 50
}
*/ 
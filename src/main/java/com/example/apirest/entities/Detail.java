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

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.envers.Audited;


@Entity
@Table(name = "detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Detail extends Base  {


    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "product_id")
    private Integer productId;
    
    @Column(name = "order_id")
    private Integer orderId;
    
    @Column(name = "size_id")
    private Integer sizeId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private PurchaseOrder purchaseOrder;
    
    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Size size;
}

/*
{
    "quantity": 2,
    "productId": 1,
    "orderId": 1,
    "sizeId": 1
}
*/ 
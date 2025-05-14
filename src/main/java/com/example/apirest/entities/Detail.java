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
@Table(name = "detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Detail extends Base  {


    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "productId")
    private Integer productId;
    
    @Column(name = "orderId")
    private Integer orderId;
}

/*
{
    "quantity": 2,
    "productId": 1,
    "orderId": 1
}
*/ 
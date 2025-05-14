package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "purchase_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class PurchaseOrder extends Base  {
    

    @Column(name = "idUserAdress")
    private Integer idUserAdress;
    
    @Column(name = "date")
    private Date date;
    
    @Column(name = "total")
    private Double total;
    
    @Column(name = "paymentMethod")
    private String paymentMethod;
    
    @Column(name = "status")
    private String status;
}

/*
{
    "idUserAdress": 1,
    "date": "2024-03-20",
    "total": 259.98,
    "paymentMethod": "Tarjeta",
    "status": "PAGADO"
}
*/ 
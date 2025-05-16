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
    //no hace falta fecha porque ya esta en base
    @Column(name = "idUser")
    private Integer idUser;

    @Column(name = "idUserAdress") //esta porque puede tener varias direcciones
    private Integer idUserAdress;
    
    @Column(name = "total")
    private Double total;
    
    @Column(name = "paymentMethod")
    private String paymentMethod;
    
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "idUserAdress", referencedColumnName = "id", insertable = false, updatable = false)
    private UsersAdress usersAdress;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
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
package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_address_id") 
    private Integer userAddressId;
    
    @Column(name = "total")
    private Double total;
    
    @Column(name = "payment_method")
    private String paymentMethod;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public enum Status {
        PENDING,
        PAID,
        CANCELLED
    }

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false),
        @JoinColumn(name = "user_address_id", referencedColumnName = "address_id", insertable = false, updatable = false)
    })
    private UsersAdress usersAdress;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}


/*
{
    "userId": 1,
    "userAddressId": 1,
    "total": 259.98,
    "paymentMethod": "Tarjeta",
    "status": "PENDING"
}
*/ 
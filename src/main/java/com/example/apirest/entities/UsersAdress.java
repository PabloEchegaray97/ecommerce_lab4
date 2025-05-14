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
@Table(name = "users_adress")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
@IdClass(UsersAdressId.class)
public class UsersAdress implements Serializable {
    
    @Id
    @Column(name = "idUser")
    private Integer idUser;

    @Id
    @Column(name = "idAdress")
    private Integer idAdress;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idAdress", referencedColumnName = "id", insertable = false, updatable = false)
    private Adress adress;
}

/*
{
    "idUser": 1,
    "idAdress": 1
}
*/ 
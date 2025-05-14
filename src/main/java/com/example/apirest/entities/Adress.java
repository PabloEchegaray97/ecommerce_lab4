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
@Table(name = "Adress")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Adress extends Base  {
    

    @Column(name = "street")
    private String street;
    
    @Column(name = "town")
    private String town;

    @Column(name = "state")
    private String state;
    
    @Column(name = "cpi")
    private String cpi;

    @Column(name = "country")
    private String country;

}

/*
{
    "cpi": "1234",
    "street": "Calle Falsa 123",
    "town": "Springfield",
    "state": "Mendoza",
    "country": "Argentina"
}
*/ 
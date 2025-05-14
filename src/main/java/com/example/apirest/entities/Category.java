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
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Category extends Base  {
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "typeId")
    private Integer typeId;
}

/*
{
    "name": "Running",
    "typeId": 1
}
*/ 
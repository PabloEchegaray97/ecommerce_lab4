package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.envers.Audited;
import java.util.List;



@Entity
@Table(name = "colour")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Colour extends Base  {
    
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

}

/*
{
    "name": "Negro",
    "value": "#000000"
}
*/ 
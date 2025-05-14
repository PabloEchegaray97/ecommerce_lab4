package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "size")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Size extends Base  {
    

    @Column(name = "number")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "systemType")
    private SystemType systemType; //// EU, US, UK, CM, etc.

    public enum SystemType {
        EU, US, UK, CM
    }

/*
Sistema	    Región	            Ejemplo
EU	        Europa	            38, 42...
US	        Estados Unidos	    7, 10.5...
UK	        Reino Unido	        6, 9...
CM	        Centímetros (Japón)	25.5, 27...
 */
}

/*
{
    "number": "38",
    "system": "EU"
}
*/ 
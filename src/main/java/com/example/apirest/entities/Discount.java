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
@Table(name = "discount")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Discount extends Base  {

    @Column(name = "start_date")
    private Date startDate;
    
    @Column(name = "end_date")
    private Date endDate;
    
    @Column(name = "discount_percentage")
    private Integer discountPercentage;
}

/*
{
    "startDate": "2024-03-01",
    "endDate": "2024-03-31",
    "discountPercentage": 10
}
*/ 
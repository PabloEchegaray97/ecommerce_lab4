package com.example.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.envers.Audited;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Product extends Base  {
    
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "colour_id")
    private Integer colourId;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "colour_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Colour colour;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Brand brand;
    
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductSize> availableSizes;

    public List<ProductSize> getAvailableSizes() {
        if (availableSizes == null) return null;
        return availableSizes.stream()
            .filter(ps -> ps.getDeletedAt() == null && Boolean.TRUE.equals(ps.getIsActive()))
            .toList();
    }

}


/*
{
    "name": "Zapatillas Nike Air Max",
    "price": 129.99,
    "description": "Zapatillas deportivas Nike Air Max con tecnología de amortiguación",
    "image": "https://ejemplo.com/nike-air-max.jpg",
    "categoryId": 1,
    "colourId": 1,  // ID del color
    "brandId": 1,   // ID de la marca
    "status": true,
    "availableSizes": [
        {
            "idSize": 1,
            "idProduct": 1,
            "stock": 25,
            "size": {
                "number": "35",
                "systemType": "EU"
            }
        }
    ]
}
*/

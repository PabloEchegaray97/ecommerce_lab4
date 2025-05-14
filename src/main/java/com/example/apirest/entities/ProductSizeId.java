package com.example.apirest.entities;

import java.io.Serializable;

public class ProductSizeId implements Serializable {
    private Integer idSize;
    private Integer idProduct;
    
    public ProductSizeId() {}
    
    public ProductSizeId(Integer idSize, Integer idProduct) {
        this.idSize = idSize;
        this.idProduct = idProduct;
    }
    
    // Getters and Setters
    public Integer getIdSize() {
        return idSize;
    }
    
    public void setIdSize(Integer idSize) {
        this.idSize = idSize;
    }
    
    public Integer getIdProduct() {
        return idProduct;
    }
    
    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
    
    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        ProductSizeId that = (ProductSizeId) o;
        
        if (!idSize.equals(that.idSize)) return false;
        return idProduct.equals(that.idProduct);
    }
    
    @Override
    public int hashCode() {
        int result = idSize.hashCode();
        result = 31 * result + idProduct.hashCode();
        return result;
    }
} 
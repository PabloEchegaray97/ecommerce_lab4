package com.example.apirest.entities;

import java.io.Serializable;

public class ProductDiscountId implements Serializable {
    private Integer idDiscount;
    private Integer idProduct;
    
    public ProductDiscountId() {}
    
    public ProductDiscountId(Integer idDiscount, Integer idProduct) {
        this.idDiscount = idDiscount;
        this.idProduct = idProduct;
    }
    
    // Getters and Setters
    public Integer getIdDiscount() {
        return idDiscount;
    }
    
    public void setIdDiscount(Integer idDiscount) {
        this.idDiscount = idDiscount;
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
        
        ProductDiscountId that = (ProductDiscountId) o;
        
        if (!idDiscount.equals(that.idDiscount)) return false;
        return idProduct.equals(that.idProduct);
    }
    
    @Override
    public int hashCode() {
        int result = idDiscount.hashCode();
        result = 31 * result + idProduct.hashCode();
        return result;
    }
} 
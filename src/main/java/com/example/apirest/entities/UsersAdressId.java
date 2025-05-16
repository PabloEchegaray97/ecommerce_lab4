package com.example.apirest.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersAdressId implements Serializable {
    private Integer userId;
    private Integer addressId;
    
    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UsersAdressId that = (UsersAdressId) o;
        
        if (!userId.equals(that.userId)) return false;
        return addressId.equals(that.addressId);
    }
    
    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + addressId.hashCode();
        return result;
    }
} 
package com.example.apirest.entities;

import java.io.Serializable;

public class UsersAdressId implements Serializable {
    private Integer idUser;
    private Integer idAdress;
    
    public UsersAdressId() {}
    
    public UsersAdressId(Integer idUser, Integer idAdress) {
        this.idUser = idUser;
        this.idAdress = idAdress;
    }
    
    // Getters and Setters
    public Integer getIdUser() {
        return idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    
    public Integer getIdAdress() {
        return idAdress;
    }
    
    public void setIdAdress(Integer idAdress) {
        this.idAdress = idAdress;
    }
    
    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UsersAdressId that = (UsersAdressId) o;
        
        if (!idUser.equals(that.idUser)) return false;
        return idAdress.equals(that.idAdress);
    }
    
    @Override
    public int hashCode() {
        int result = idUser.hashCode();
        result = 31 * result + idAdress.hashCode();
        return result;
    }
} 
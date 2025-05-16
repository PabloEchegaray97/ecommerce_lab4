package com.example.apirest.repositories;

import com.example.apirest.entities.UsersAdress;
import com.example.apirest.entities.UsersAdressId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "userAddresses")
public interface UsersAdressRepository extends JpaRepository<UsersAdress, UsersAdressId> {
    
    @RestResource(path = "byUser")
    List<UsersAdress> findByUserId(@Param("userId") Integer userId);
    
    @RestResource(path = "byAdress")
    List<UsersAdress> findByAddressId(@Param("addressId") Integer addressId);
} 
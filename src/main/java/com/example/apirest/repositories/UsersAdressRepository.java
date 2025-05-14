package com.example.apirest.repositories;

import com.example.apirest.entities.UsersAdress;
import com.example.apirest.entities.UsersAdressId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAdressRepository extends JpaRepository<UsersAdress, UsersAdressId> {
} 
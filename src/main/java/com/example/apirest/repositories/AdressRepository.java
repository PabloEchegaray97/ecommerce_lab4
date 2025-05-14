package com.example.apirest.repositories;

import com.example.apirest.entities.Adress;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "addresses")
public interface AdressRepository extends BaseRepository<Adress, Integer> {
} 
package com.example.apirest.repositories;

import com.example.apirest.entities.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "users")
public interface UserRepository extends BaseRepository<User, Integer> {
} 
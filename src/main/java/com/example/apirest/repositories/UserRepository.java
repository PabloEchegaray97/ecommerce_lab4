package com.example.apirest.repositories;

import com.example.apirest.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {
} 
package com.example.apirest.repositories;

import com.example.apirest.entities.Colour;
import org.springframework.stereotype.Repository;

@Repository
public interface ColourRepository extends BaseRepository<Colour, Integer> {
} 
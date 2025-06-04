package com.example.apirest.repositories;

import com.example.apirest.entities.Brand;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends BaseRepository<Brand, Integer> {
} 
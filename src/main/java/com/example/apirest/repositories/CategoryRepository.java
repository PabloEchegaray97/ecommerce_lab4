package com.example.apirest.repositories;

import com.example.apirest.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer> {
} 
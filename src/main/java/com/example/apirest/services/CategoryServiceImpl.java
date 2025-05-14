package com.example.apirest.services;

import com.example.apirest.entities.Category;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Integer> {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(BaseRepository<Category, Integer> baseRepository) {
        super(baseRepository);
    }
    
    
} 
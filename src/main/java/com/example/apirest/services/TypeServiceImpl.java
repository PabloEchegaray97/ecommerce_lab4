package com.example.apirest.services;

import com.example.apirest.entities.Type;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl extends BaseServiceImpl<Type, Integer> {
    
    @Autowired
    private TypeRepository typeRepository;
    
    public TypeServiceImpl(BaseRepository<Type, Integer> baseRepository) {
        super(baseRepository);
    }
} 
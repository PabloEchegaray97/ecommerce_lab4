package com.example.apirest.services;

import com.example.apirest.entities.Colour;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.ColourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColourServiceImpl extends BaseServiceImpl<Colour, Integer> {
    
    @Autowired
    private ColourRepository colourRepository;
    
    public ColourServiceImpl(BaseRepository<Colour, Integer> baseRepository) {
        super(baseRepository);
    }
} 
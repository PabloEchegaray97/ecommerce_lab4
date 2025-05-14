package com.example.apirest.services;

import com.example.apirest.entities.Adress;
import com.example.apirest.repositories.AdressRepository;
import com.example.apirest.repositories.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdressServiceImpl extends BaseServiceImpl<Adress, Integer> {
    
    @Autowired
    private AdressRepository adressRepository;
    
    public AdressServiceImpl(BaseRepository<Adress, Integer> baseRepository) {
        super(baseRepository);
    }
    
    
} 
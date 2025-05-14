package com.example.apirest.services;

import com.example.apirest.entities.Size;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl extends BaseServiceImpl<Size, Integer> {
    
    @Autowired
    private SizeRepository sizeRepository;
    
    public SizeServiceImpl(BaseRepository<Size, Integer> baseRepository) {
        super(baseRepository);
    }
    
}
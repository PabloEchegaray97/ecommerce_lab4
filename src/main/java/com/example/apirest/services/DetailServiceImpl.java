package com.example.apirest.services;

import com.example.apirest.entities.Detail;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailServiceImpl extends BaseServiceImpl<Detail, Integer> {
    
    @Autowired
    private DetailRepository detailRepository;
    
    public DetailServiceImpl(BaseRepository<Detail, Integer> baseRepository) {
        super(baseRepository);
    }
} 
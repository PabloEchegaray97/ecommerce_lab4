package com.example.apirest.services;

import com.example.apirest.entities.User;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> {
    
    @Autowired
    private UserRepository userRepository;
    
    public UserServiceImpl(BaseRepository<User, Integer> baseRepository) {
        super(baseRepository);
    }
    
} 
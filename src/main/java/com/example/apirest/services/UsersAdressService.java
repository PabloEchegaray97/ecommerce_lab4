package com.example.apirest.services;

import com.example.apirest.entities.UsersAdress;
import com.example.apirest.entities.UsersAdressId;
import com.example.apirest.repositories.UsersAdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UsersAdressService {
    
    @Autowired
    private UsersAdressRepository usersAdressRepository;
    
    @Transactional
    public List<UsersAdress> findAll() throws Exception {
        try {
            return usersAdressRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public UsersAdress findById(UsersAdressId id) throws Exception {
        try {
            Optional<UsersAdress> entityOptional = usersAdressRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public List<UsersAdress> findByUserId(Integer userId) throws Exception {
        try {
            return usersAdressRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public UsersAdress save(UsersAdress entity) throws Exception {
        try {
            entity = usersAdressRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public UsersAdress update(UsersAdressId id, UsersAdress entity) throws Exception {
        try {
            Optional<UsersAdress> entityOptional = usersAdressRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el registro con id: " + id);
            }
            UsersAdress usersAdress = entityOptional.get();
            BeanUtils.copyProperties(entity, usersAdress, "userId", "addressId");    
            return usersAdress;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public boolean delete(UsersAdressId id) throws Exception {
        try {
            if (usersAdressRepository.existsById(id)) {
                usersAdressRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No existe el registro con id: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
} 
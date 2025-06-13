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
            List<UsersAdress> addresses = usersAdressRepository.findAll();
            if (addresses.isEmpty()) {
                throw new Exception("No se encontraron direcciones");
            }
            return addresses;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public UsersAdress findById(UsersAdressId id) throws Exception {
        try {
            Optional<UsersAdress> entityOptional = usersAdressRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró la dirección con id: " + id);
            }
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public List<UsersAdress> findByUserId(Integer userId) throws Exception {
        try {
            List<UsersAdress> addresses = usersAdressRepository.findByUserId(userId);
            if (addresses.isEmpty()) {
                throw new Exception("No se encontraron direcciones para el usuario con id: " + userId);
            }
            return addresses;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public UsersAdress save(UsersAdress entity) throws Exception {
        try {
            if (entity.getUserId() == null || entity.getAddressId() == null) {
                throw new Exception("El userId y addressId son requeridos");
            }
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
                throw new Exception("No se encontró la dirección con id: " + id);
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
                throw new Exception("No existe la dirección con id: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
} 
package com.example.apirest.controllers;

import com.example.apirest.entities.UsersAdress;
import com.example.apirest.entities.UsersAdressId;
import com.example.apirest.services.UsersAdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/user-addresses")
public class UsersAdressController {

    @Autowired
    private UsersAdressService usersAdressService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usersAdressService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{userId}/{addressId}")
    public ResponseEntity<?> getOne(@PathVariable Integer userId, @PathVariable Integer addressId) {
        try {
            UsersAdressId id = new UsersAdressId(userId, addressId);
            return ResponseEntity.status(HttpStatus.OK).body(usersAdressService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usersAdressService.findByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody UsersAdress entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usersAdressService.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{userId}/{addressId}")
    public ResponseEntity<?> update(@PathVariable Integer userId, @PathVariable Integer addressId, @RequestBody UsersAdress entity) {
        try {
            UsersAdressId id = new UsersAdressId(userId, addressId);
            return ResponseEntity.status(HttpStatus.OK).body(usersAdressService.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/{userId}/{addressId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId, @PathVariable Integer addressId) {
        try {
            UsersAdressId id = new UsersAdressId(userId, addressId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usersAdressService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
} 
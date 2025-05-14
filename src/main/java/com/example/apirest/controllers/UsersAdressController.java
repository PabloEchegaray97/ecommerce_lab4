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
@RequestMapping(path = "api/v1/users-addresses")
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

    @GetMapping("/{idUser}/{idAdress}")
    public ResponseEntity<?> getOne(@PathVariable Integer idUser, @PathVariable Integer idAdress) {
        try {
            UsersAdressId id = new UsersAdressId(idUser, idAdress);
            return ResponseEntity.status(HttpStatus.OK).body(usersAdressService.findById(id));
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

    @PutMapping("/{idUser}/{idAdress}")
    public ResponseEntity<?> update(@PathVariable Integer idUser, @PathVariable Integer idAdress, @RequestBody UsersAdress entity) {
        try {
            UsersAdressId id = new UsersAdressId(idUser, idAdress);
            return ResponseEntity.status(HttpStatus.OK).body(usersAdressService.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/{idUser}/{idAdress}")
    public ResponseEntity<?> delete(@PathVariable Integer idUser, @PathVariable Integer idAdress) {
        try {
            UsersAdressId id = new UsersAdressId(idUser, idAdress);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usersAdressService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
} 
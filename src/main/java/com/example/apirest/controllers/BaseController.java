package com.example.apirest.controllers;

import com.example.apirest.entities.Base;
import com.example.apirest.services.BaseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract class BaseController<E extends Base, ID extends Serializable> {

    protected BaseService<E, ID> service;

    public BaseController(BaseService<E, ID> service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> getAll() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable ID id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody E entity) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody E entity) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ID id) throws Exception {
        try {
            service.delete(id); 
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Entity deleted successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}

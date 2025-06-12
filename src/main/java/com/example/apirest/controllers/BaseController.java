package com.example.apirest.controllers;

import com.example.apirest.entities.Base;
import com.example.apirest.services.BaseService;

import org.springframework.data.domain.Pageable;
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

    @GetMapping("/paged")
    public ResponseEntity<?> getAll(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
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
    
    // Nuevos endpoints para manejo de estados
    @GetMapping("/active")
    public ResponseEntity<?> getAllActive() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllActive());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/active/paged")
    public ResponseEntity<?> getAllActive(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllActive(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/inactive")
    public ResponseEntity<?> getAllInactive() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllInactive());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/inactive/paged")
    public ResponseEntity<?> getAllInactive(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllInactive(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/soft-deleted")
    public ResponseEntity<?> getAllSoftDeleted() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllSoftDeleted());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/soft-deleted/paged")
    public ResponseEntity<?> getAllSoftDeleted(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllSoftDeleted(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable ID id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.activate(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable ID id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.deactivate(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}/soft-delete")
    public ResponseEntity<?> softDelete(@PathVariable ID id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.softDelete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<?> restore(@PathVariable ID id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.restore(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}

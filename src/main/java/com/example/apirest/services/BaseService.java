package com.example.apirest.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.apirest.entities.Base;

public interface BaseService<E extends Base, ID extends Serializable> {
    public List<E> findAll() throws Exception;
    public Page<E> findAll(Pageable pageable) throws Exception;
    public E findById(ID id) throws Exception;
    public E save(E entity) throws Exception;
    public E update(ID id, E entity) throws Exception;
    public boolean delete(ID id) throws Exception;
    
    public List<E> findAllActive() throws Exception;
    public Page<E> findAllActive(Pageable pageable) throws Exception;
    public List<E> findAllInactive() throws Exception;
    public Page<E> findAllInactive(Pageable pageable) throws Exception;
    public List<E> findAllSoftDeleted() throws Exception;
    public Page<E> findAllSoftDeleted(Pageable pageable) throws Exception;
    
    public E activate(ID id) throws Exception;
    public E deactivate(ID id) throws Exception;
    public E softDelete(ID id) throws Exception;
    public E restore(ID id) throws Exception;
}

package com.example.apirest.services;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.BeanUtils;

import com.example.apirest.entities.Base;
import com.example.apirest.repositories.BaseRepository;

import jakarta.transaction.Transactional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;
    
    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            List<E> entities = baseRepository.findAllActive();
            if (entities.isEmpty()) {
                throw new Exception("No se encontró el recurso solicitado");
            }
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception {
        try {
            Page<E> entities = baseRepository.findAllActive(pageable);
            if (entities.isEmpty()) {
                throw new Exception("No se encontró el recurso solicitado");
            }
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el recurso con id: " + id);
            }
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el producto con id: " + id);
            }
            E entityToUpdate = entityOptional.get();
            LocalDateTime createdAt = entityToUpdate.getCreatedAt();
            BeanUtils.copyProperties(entity, entityToUpdate, "id", "createdAt");
            entityToUpdate.setCreatedAt(createdAt);
            return baseRepository.save(entityToUpdate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No se encontró el recurso con id: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public List<E> findAllActive() throws Exception {
        try {
            List<E> entities = baseRepository.findAllActive();
            if (entities.isEmpty()) {
                throw new Exception("No se encontraron recursos activos");
            }
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAllActive(Pageable pageable) throws Exception {
        try {
            Page<E> entities = baseRepository.findAllActive(pageable);
            if (entities.isEmpty()) {
                throw new Exception("No se encontraron recursos activos");
            }
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<E> findAllInactive() throws Exception {
        try {
            List<E> entities = baseRepository.findAllInactive();
            if (entities.isEmpty()) {
                throw new Exception("No se encontraron recursos inactivos");
            }
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAllInactive(Pageable pageable) throws Exception {
        try {
            Page<E> entities = baseRepository.findAllInactive(pageable);
            if (entities.isEmpty()) {
                throw new Exception("No se encontraron recursos inactivos");
            }
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<E> findAllSoftDeleted() throws Exception {
        try {
            List<E> entities = baseRepository.findAllSoftDeleted();
            if (entities.isEmpty()) {
                throw new Exception("No se encontraron recursos eliminados");
            }
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAllSoftDeleted(Pageable pageable) throws Exception {
        try {
            Page<E> entities = baseRepository.findAllSoftDeleted(pageable);
            if (entities.isEmpty()) {
                throw new Exception("No se encontraron recursos eliminados");
            }
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E activate(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el recurso con id: " + id);
            }
            E entity = entityOptional.get();
            entity.setIsActive(true);
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E deactivate(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el recurso con id: " + id);
            }
            E entity = entityOptional.get();
            entity.setIsActive(false);
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E softDelete(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el recurso con id: " + id);
            }
            E entity = entityOptional.get();
            entity.setDeletedAt(LocalDateTime.now());
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E restore(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (!entityOptional.isPresent()) {
                throw new Exception("No se encontró el recurso con id: " + id);
            }
            E entity = entityOptional.get();
            if (entity.getDeletedAt() == null) {
                throw new Exception("El recurso con id: " + id + " no está eliminado");
            }
            entity.setDeletedAt(null);
            entity.setIsActive(true);
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

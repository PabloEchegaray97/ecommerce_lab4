package com.example.apirest.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.apirest.entities.Base;

@NoRepositoryBean
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {
    
    // Consultas para elementos activos (isActive = true y deletedAt = null)
    @Query("SELECT e FROM #{#entityName} e WHERE e.isActive = true AND e.deletedAt IS NULL")
    List<E> findAllActive();
    
    @Query("SELECT e FROM #{#entityName} e WHERE e.isActive = true AND e.deletedAt IS NULL")
    Page<E> findAllActive(Pageable pageable);
    
    // Consultas para elementos inactivos (isActive = false y deletedAt = null)
    @Query("SELECT e FROM #{#entityName} e WHERE e.isActive = false AND e.deletedAt IS NULL")
    List<E> findAllInactive();
    
    @Query("SELECT e FROM #{#entityName} e WHERE e.isActive = false AND e.deletedAt IS NULL")
    Page<E> findAllInactive(Pageable pageable);
    
    // Consultas para elementos soft deleted (deletedAt IS NOT NULL)
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NOT NULL")
    List<E> findAllSoftDeleted();
    
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NOT NULL")
    Page<E> findAllSoftDeleted(Pageable pageable);
}

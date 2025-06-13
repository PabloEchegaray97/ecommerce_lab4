package com.example.apirest.repositories;

import com.example.apirest.entities.Detail;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetailRepository extends BaseRepository<Detail, Integer> {
    List<Detail> findByOrderId(Integer orderId);
} 
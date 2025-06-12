package com.example.apirest.repositories;

import com.example.apirest.entities.PurchaseOrder;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrder, Integer> {
    List<PurchaseOrder> findByUserId(Integer userId);
} 
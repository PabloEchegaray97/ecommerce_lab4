package com.example.apirest.repositories;

import com.example.apirest.entities.PurchaseOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrder, Integer> {
} 
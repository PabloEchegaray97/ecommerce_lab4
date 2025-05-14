package com.example.apirest.services;

import com.example.apirest.entities.PurchaseOrder;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderServiceImpl extends BaseServiceImpl<PurchaseOrder, Integer> {
    
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    
    public PurchaseOrderServiceImpl(BaseRepository<PurchaseOrder, Integer> baseRepository) {
        super(baseRepository);
    }
    
    
} 
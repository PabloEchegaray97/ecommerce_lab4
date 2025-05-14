package com.example.apirest.controllers;

import com.example.apirest.entities.PurchaseOrder;
import com.example.apirest.services.PurchaseOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/purchase-orders")
public class PurchaseOrderController extends BaseController<PurchaseOrder, Integer>{

    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderServiceImpl;

    public PurchaseOrderController(PurchaseOrderServiceImpl purchaseOrderServiceImpl) {
        super(purchaseOrderServiceImpl);
    }
}
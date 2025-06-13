package com.example.apirest.services;

import com.example.apirest.entities.PurchaseOrder;
import com.example.apirest.entities.Detail;
import com.example.apirest.entities.ProductSize;
import com.example.apirest.entities.ProductSizeId;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.PurchaseOrderRepository;
import com.example.apirest.repositories.DetailRepository;
import com.example.apirest.repositories.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl extends BaseServiceImpl<PurchaseOrder, Integer> {
    
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    
    public PurchaseOrderServiceImpl(BaseRepository<PurchaseOrder, Integer> baseRepository) {
        super(baseRepository);
    }
    
    public List<PurchaseOrder> findByUserId(Integer userId) throws Exception {
        try {
            return purchaseOrderRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PurchaseOrder save(PurchaseOrder entity) throws Exception {
        PurchaseOrder savedOrder = super.save(entity);
        // Restar stock por cada detalle
        List<Detail> detalles = detailRepository.findByOrderId(savedOrder.getId());
        for (Detail detalle : detalles) {
            ProductSizeId psId = new ProductSizeId(detalle.getSizeId(), detalle.getProductId());
            ProductSize ps = productSizeRepository.findById(psId).orElse(null);
            if (ps != null) {
                int nuevoStock = ps.getStock() - detalle.getQuantity();
                ps.setStock(Math.max(nuevoStock, 0));
                productSizeRepository.save(ps);
            }
        }
        return savedOrder;
    }

    @Override
    @Transactional
    public PurchaseOrder update(Integer id, PurchaseOrder entity) throws Exception {
        // Si se cancela la orden, devolver stock
        PurchaseOrder original = purchaseOrderRepository.findById(id).orElse(null);
        if (original != null && original.getStatus() != PurchaseOrder.Status.CANCELLED && entity.getStatus() == PurchaseOrder.Status.CANCELLED) {
            List<Detail> detalles = detailRepository.findByOrderId(id);
            for (Detail detalle : detalles) {
                ProductSizeId psId = new ProductSizeId(detalle.getSizeId(), detalle.getProductId());
                ProductSize ps = productSizeRepository.findById(psId).orElse(null);
                if (ps != null) {
                    ps.setStock(ps.getStock() + detalle.getQuantity());
                    productSizeRepository.save(ps);
                }
            }
        }
        return super.update(id, entity);
    }
} 
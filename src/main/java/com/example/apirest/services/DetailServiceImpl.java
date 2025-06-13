package com.example.apirest.services;

import com.example.apirest.entities.Detail;
import com.example.apirest.entities.ProductSize;
import com.example.apirest.entities.ProductSizeId;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.DetailRepository;
import com.example.apirest.repositories.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetailServiceImpl extends BaseServiceImpl<Detail, Integer> {
    
    @Autowired
    private DetailRepository detailRepository;
    
    @Autowired
    private ProductSizeRepository productSizeRepository;
    
    public DetailServiceImpl(BaseRepository<Detail, Integer> baseRepository) {
        super(baseRepository);
    }
    
    @Override
    @Transactional
    public Detail save(Detail entity) throws Exception {
        // Verificar que hay stock suficiente antes de crear el detalle
        ProductSizeId psId = new ProductSizeId(entity.getSizeId(), entity.getProductId());
        ProductSize productSize = productSizeRepository.findById(psId).orElse(null);
        
        if (productSize == null) {
            throw new Exception("No se encontró el producto con el talle especificado");
        }
        
        if (productSize.getStock() < entity.getQuantity()) {
            throw new Exception("Stock insuficiente. Stock disponible: " + productSize.getStock() + 
                              ", cantidad solicitada: " + entity.getQuantity());
        }
        
        // Restar el stock
        int nuevoStock = productSize.getStock() - entity.getQuantity();
        productSize.setStock(Math.max(nuevoStock, 0));
        productSizeRepository.save(productSize);
        
        // Guardar el detalle
        Detail savedDetail = super.save(entity);
        
        System.out.println("=== STOCK ACTUALIZADO ===");
        System.out.println("Producto ID: " + entity.getProductId());
        System.out.println("Talle ID: " + entity.getSizeId());
        System.out.println("Cantidad ordenada: " + entity.getQuantity());
        System.out.println("Stock anterior: " + (productSize.getStock() + entity.getQuantity()));
        System.out.println("Stock actual: " + productSize.getStock());
        
        return savedDetail;
    }
    
    @Override
    @Transactional
    public boolean delete(Integer id) throws Exception {
        // Antes de eliminar el detalle, devolver el stock
        Detail detail = this.findById(id);
        if (detail != null) {
            ProductSizeId psId = new ProductSizeId(detail.getSizeId(), detail.getProductId());
            ProductSize productSize = productSizeRepository.findById(psId).orElse(null);
            
            if (productSize != null) {
                // Devolver el stock
                productSize.setStock(productSize.getStock() + detail.getQuantity());
                productSizeRepository.save(productSize);
                
                System.out.println("=== STOCK DEVUELTO ===");
                System.out.println("Producto ID: " + detail.getProductId());
                System.out.println("Talle ID: " + detail.getSizeId());
                System.out.println("Cantidad devuelta: " + detail.getQuantity());
                System.out.println("Stock actualizado: " + productSize.getStock());
            }
        }
        
        return super.delete(id);
    }
} 
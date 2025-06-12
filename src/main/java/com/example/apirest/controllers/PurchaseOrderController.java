package com.example.apirest.controllers;

import com.example.apirest.entities.PurchaseOrder;
import com.example.apirest.entities.User;
import com.example.apirest.services.PurchaseOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/purchase-orders")
public class PurchaseOrderController extends BaseController<PurchaseOrder, Integer>{

    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderServiceImpl;

    public PurchaseOrderController(PurchaseOrderServiceImpl purchaseOrderServiceImpl) {
        super(purchaseOrderServiceImpl);
    }
    
    @GetMapping("/my-orders")
    public ResponseEntity<?> getMyOrders() {
        try {
            // Obtener el usuario logueado desde el contexto de seguridad
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            
            // Obtener las órdenes del usuario logueado
            return ResponseEntity.status(HttpStatus.OK)
                .body(purchaseOrderServiceImpl.findByUserId(currentUser.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    @PutMapping("/{orderId}/approve")
    public ResponseEntity<?> approveOrder(@PathVariable Integer orderId, @RequestBody Map<String, String> requestBody) {
        try {
            String paymentId = requestBody.get("paymentId");
            
            // Obtener la orden
            PurchaseOrder order = purchaseOrderServiceImpl.findById(orderId);
            
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Orden no encontrada\"}");
            }
            
            // Actualizar el estado y el paymentId
            order.setStatus(PurchaseOrder.Status.PAID);
            order.setPaymentId(paymentId);
            
            // Guardar los cambios
            PurchaseOrder updatedOrder = purchaseOrderServiceImpl.update(orderId, order);
            
            return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
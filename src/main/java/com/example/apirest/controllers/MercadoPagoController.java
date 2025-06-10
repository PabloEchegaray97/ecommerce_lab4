package com.example.apirest.controllers;

import com.example.apirest.dto.PaymentRequestDTO;
import com.example.apirest.dto.PaymentResponseDTO;
import com.example.apirest.entities.PurchaseOrder;
import com.example.apirest.services.MercadoPagoService;
import com.example.apirest.services.PurchaseOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/mercadopago")
@CrossOrigin(origins = "*")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderService;

    @PostMapping("/create-preference")
    public ResponseEntity<?> createPreference(@RequestBody PaymentRequestDTO paymentRequest) {
        try {
            // Para este ejemplo, creamos una orden temporal
            // En un caso real, deberías recibir el ID de la orden existente
            PurchaseOrder tempOrder = new PurchaseOrder();
            tempOrder.setId(1); // ID temporal
            
            PaymentResponseDTO response = mercadoPagoService.createPreference(paymentRequest, tempOrder);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear la preferencia de pago", "message", e.getMessage()));
        }
    }

    @PostMapping("/create-preference-from-order/{orderId}")
    public ResponseEntity<?> createPreferenceFromOrder(@PathVariable Integer orderId) {
        try {
            PurchaseOrder order = purchaseOrderService.findById(orderId);
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Orden no encontrada"));
            }

            PaymentResponseDTO response = mercadoPagoService.createPreferenceFromOrder(order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear la preferencia de pago", "message", e.getMessage()));
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> handleWebhook(@RequestBody Map<String, Object> payload) {
        try {
            System.out.println("Webhook recibido de Mercado Pago: " + payload);
            
            // Aquí puedes procesar las notificaciones de Mercado Pago
            String type = (String) payload.get("type");
            
            if ("payment".equals(type)) {
                Map<String, Object> data = (Map<String, Object>) payload.get("data");
                String paymentId = (String) data.get("id");
                
                // Aquí deberías consultar el estado del pago y actualizar tu orden
                System.out.println("Pago recibido con ID: " + paymentId);
                
                // Ejemplo de cómo actualizar el estado de la orden
                // updateOrderStatus(paymentId);
            }
            
            return ResponseEntity.ok(Map.of("status", "received"));
        } catch (Exception e) {
            System.err.println("Error procesando webhook: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error procesando webhook"));
        }
    }

    @GetMapping("/payment-status/{orderId}")
    public ResponseEntity<?> getPaymentStatus(@PathVariable Integer orderId) {
        try {
            PurchaseOrder order = purchaseOrderService.findById(orderId);
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Orden no encontrada"));
            }

            return ResponseEntity.ok(Map.of(
                    "orderId", order.getId(),
                    "status", order.getStatus(),
                    "total", order.getTotal(),
                    "paymentMethod", order.getPaymentMethod()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al consultar el estado del pago", "message", e.getMessage()));
        }
    }
} 
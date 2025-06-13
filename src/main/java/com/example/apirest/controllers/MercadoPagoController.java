package com.example.apirest.controllers;

import com.example.apirest.entities.PurchaseOrder;
import com.example.apirest.services.PurchaseOrderServiceImpl;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pay")
@CrossOrigin("*")
public class MercadoPagoController {

    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderService;

    @Value("${mercadopago.access-token}")
    private String mercadoPagoAccessToken;

    @PostMapping("/mp")
    public ResponseEntity<String> mp(@RequestBody Map<String, List<Long>> body) throws Exception {
        List<Long> ids = body.get("id");
        MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
        List<PreferenceItemRequest> items = new ArrayList<>();

        // Usar la primera orden para el ejemplo
        PurchaseOrder order = purchaseOrderService.findById(ids.get(0).intValue());
        
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .id(order.getId().toString())
                .title("Orden de Compra #" + order.getId())
                .description("Pago de orden de compra de zapatillas")
                .quantity(1)
                .currencyId("ARS")
                .unitPrice(BigDecimal.valueOf(order.getTotal()))
                .build();
        items.add(item);

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://localhost:5173/paymentSuccess")
                .pending("https://localhost:5173/paymentPending")
                .failure("https://localhost:5173/paymentFailure")
                .build();

        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

        PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                .excludedPaymentTypes(excludedPaymentTypes)
                .installments(1)
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(backUrls)
                .paymentMethods(paymentMethods)
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        String prefId = preference.getId();
        String paymentUrl = preference.getInitPoint();
        System.out.println("URL de pago: " + paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body("{\"preferenceId\":\""+prefId+"\",\"paymentUrl\":\""+paymentUrl+"\"}");
    }

    @PostMapping("/test")
    public ResponseEntity<String> test() throws Exception {
        MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
        List<PreferenceItemRequest> items = new ArrayList<>();

        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .id("test-100")
                .title("Producto de Prueba")
                .description("Producto de $100 para probar MercadoPago")
                .quantity(1)
                .currencyId("ARS")
                .unitPrice(BigDecimal.valueOf(100.0))
                .build();
        items.add(item);

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("http://localhost:5173/paymentSuccess")
                .pending("http://localhost:5173/paymentPending")
                .failure("http://localhost:5173/paymentFailure")
                .build();

        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

        PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                .excludedPaymentTypes(excludedPaymentTypes)
                .installments(1)
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(backUrls)
                .paymentMethods(paymentMethods)
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        String prefId = preference.getId();
        String paymentUrl = preference.getInitPoint();
        System.out.println("URL de pago: " + paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body("{\"preferenceId\":\""+prefId+"\",\"paymentUrl\":\""+paymentUrl+"\"}");
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<String> createPaymentForOrder(@PathVariable Integer orderId) throws Exception {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
            
            // Obtener la orden
            PurchaseOrder order = purchaseOrderService.findById(orderId);
            
            if (order.getStatus() == PurchaseOrder.Status.PAID) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"La orden ya está pagada\"}");
            }
            
            List<PreferenceItemRequest> items = new ArrayList<>();

            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .id("order-" + order.getId())
                    .title("Orden de Compra #" + order.getId())
                    .description("Pago de orden de compra de zapatillas")
                    .quantity(1)
                    .currencyId("ARS")
                    .unitPrice(BigDecimal.valueOf(order.getTotal()))
                    .build();
            items.add(item);

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://localhost:5173/paymentSuccess?orderId=" + orderId)
                    .pending("https://localhost:5173/paymentPending?orderId=" + orderId)
                    .failure("https://localhost:5173/paymentFailure?orderId=" + orderId)
                    .build();

            List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
            excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

            PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                    .excludedPaymentTypes(excludedPaymentTypes)
                    .installments(1)
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .paymentMethods(paymentMethods)
                    .externalReference(orderId.toString()) // Referencia a la orden
                    .autoReturn("approved")
                    .statementDescriptor("Vans")
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            String prefId = preference.getId();
            String paymentUrl = preference.getInitPoint();
            
            System.out.println("=== PAGO PARA ORDEN ===");
            System.out.println("Order ID: " + orderId);
            System.out.println("Order Total: $" + order.getTotal());
            System.out.println("Preference ID: " + prefId);
            System.out.println("Payment URL: " + paymentUrl);

            return ResponseEntity.status(HttpStatus.OK)
                .body("{\"preferenceId\":\""+prefId+"\",\"paymentUrl\":\""+paymentUrl+"\",\"orderId\":"+orderId+",\"orderTotal\":"+order.getTotal()+"}");
                
        } catch (Exception e) {
            System.err.println("Error creando pago para orden " + orderId + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\":\"Error al crear el pago: " + e.getMessage() + "\"}");
        }
    }

    // Endpoint para verificar el estado de una orden
    @GetMapping("/order-status/{orderId}")
    public ResponseEntity<String> getOrderStatus(@PathVariable Integer orderId) {
        try {
            PurchaseOrder order = purchaseOrderService.findById(orderId);
            
            String response = "{" +
                "\"orderId\":" + order.getId() + "," +
                "\"status\":\"" + order.getStatus() + "\"," +
                "\"total\":" + order.getTotal() + "," +
                "\"paymentMethod\":\"" + order.getPaymentMethod() + "\"," +
                "\"isPaid\":" + (order.getStatus() == PurchaseOrder.Status.PAID) +
                "}";
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"error\":\"Orden no encontrada\"}");
        }
    }

    // Endpoint para confirmar el pago con los parámetros de MercadoPago
    @PostMapping("/confirm-payment")
    public ResponseEntity<String> confirmPayment(@RequestBody Map<String, String> paymentData) {
        try {
            String orderIdStr = paymentData.get("orderId");
            String paymentId = paymentData.get("payment_id");
            String status = paymentData.get("status");
            String collectionStatus = paymentData.get("collection_status");
            
            if (orderIdStr == null || paymentId == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Faltan parámetros requeridos: orderId y payment_id\"}");
            }
            
            Integer orderId = Integer.parseInt(orderIdStr);
            PurchaseOrder order = purchaseOrderService.findById(orderId);
            
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Orden no encontrada\"}");
            }
            
            // Verificar si el pago fue aprobado
            boolean isApproved = "approved".equals(status) || "approved".equals(collectionStatus);
            
            if (isApproved) {
                // Actualizar la orden como pagada
                order.setStatus(PurchaseOrder.Status.PAID);
                order.setPaymentId(paymentId);
                order.setPaymentMethod("MercadoPago");
                
                PurchaseOrder updatedOrder = purchaseOrderService.update(orderId, order);
                
                System.out.println("=== PAGO CONFIRMADO ===");
                System.out.println("Orden ID: " + orderId);
                System.out.println("Payment ID: " + paymentId);
                System.out.println("Estado: " + status);
                System.out.println("Orden actualizada a PAID");
                
                return ResponseEntity.ok("{" +
                    "\"success\":true," +
                    "\"message\":\"Pago confirmado exitosamente\"," +
                    "\"orderId\":" + orderId + "," +
                    "\"paymentId\":\"" + paymentId + "\"," +
                    "\"status\":\"PAID\"" +
                    "}");
            } else {
                System.out.println("=== PAGO RECHAZADO ===");
                System.out.println("Orden ID: " + orderId);
                System.out.println("Payment ID: " + paymentId);
                System.out.println("Estado: " + status);
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"El pago no fue aprobado\",\"status\":\"" + status + "\"}");
            }
            
        } catch (Exception e) {
            System.err.println("Error confirmando pago: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\":\"Error al confirmar el pago: " + e.getMessage() + "\"}");
        }
    }

    // Endpoint alternativo: confirmar pago directamente con parámetros GET (más simple)
    @GetMapping("/confirm-payment/{orderId}")
    public ResponseEntity<String> confirmPaymentGet(
            @PathVariable Integer orderId,
            @RequestParam(required = false) String payment_id,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String collection_status) {
        
        try {
            if (payment_id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Falta el payment_id\"}");
            }
            
            PurchaseOrder order = purchaseOrderService.findById(orderId);
            
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Orden no encontrada\"}");
            }
            
            // Verificar si el pago fue aprobado
            boolean isApproved = "approved".equals(status) || "approved".equals(collection_status);
            
            if (isApproved) {
                // Actualizar la orden como pagada
                order.setStatus(PurchaseOrder.Status.PAID);
                order.setPaymentId(payment_id);
                order.setPaymentMethod("MercadoPago");
                
                PurchaseOrder updatedOrder = purchaseOrderService.update(orderId, order);
                
                System.out.println("=== PAGO CONFIRMADO (GET) ===");
                System.out.println("Orden ID: " + orderId);
                System.out.println("Payment ID: " + payment_id);
                System.out.println("Estado: " + status);
                
                return ResponseEntity.ok("{" +
                    "\"success\":true," +
                    "\"message\":\"Pago confirmado exitosamente\"," +
                    "\"orderId\":" + orderId + "," +
                    "\"paymentId\":\"" + payment_id + "\"," +
                    "\"status\":\"PAID\"" +
                    "}");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"El pago no fue aprobado\",\"status\":\"" + status + "\"}");
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\":\"Error al confirmar el pago: " + e.getMessage() + "\"}");
        }
    }
} 
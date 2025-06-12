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
} 
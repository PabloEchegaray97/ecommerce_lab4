package com.example.apirest.services;

import com.example.apirest.dto.PaymentRequestDTO;
import com.example.apirest.dto.PaymentResponseDTO;
import com.example.apirest.entities.PurchaseOrder;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoPagoService {

    @Value("${mercadopago.access.token}")
    private String accessToken;

    @Value("${mercadopago.webhook.url}")
    private String webhookUrl;

    @PostConstruct
    public void init() {
        MercadoPagoConfig.setAccessToken(accessToken);
    }

    public PaymentResponseDTO createPreference(PaymentRequestDTO paymentRequest, PurchaseOrder order) {
        try {
            PreferenceClient client = new PreferenceClient();

            // Crear items
            List<PreferenceItemRequest> items = new ArrayList<>();
            for (PaymentRequestDTO.ItemDTO item : paymentRequest.getItems()) {
                PreferenceItemRequest preferenceItem = PreferenceItemRequest.builder()
                        .title(item.getTitle())
                        .description(item.getDescription())
                        .quantity(item.getQuantity())
                        .unitPrice(BigDecimal.valueOf(item.getUnitPrice()))
                        .currencyId(item.getCurrencyId())
                        .build();
                items.add(preferenceItem);
            }

            // Configurar URLs de retorno válidas
            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://www.mercadopago.com.ar/checkout/v1/redirect?pref_id=success")
                    .failure("https://www.mercadopago.com.ar/checkout/v1/redirect?pref_id=failure")
                    .pending("https://www.mercadopago.com.ar/checkout/v1/redirect?pref_id=pending")
                    .build();

            // Configurar pagador
            PreferencePayerRequest payer = null;
            if (paymentRequest.getPayer() != null) {
                payer = PreferencePayerRequest.builder()
                        .name(paymentRequest.getPayer().getName())
                        .surname(paymentRequest.getPayer().getSurname())
                        .email(paymentRequest.getPayer().getEmail())
                        .build();
            }

            // Crear la preferencia
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .payer(payer)
                    .notificationUrl(webhookUrl)
                    .externalReference(order.getId().toString())
                    .build();

            Preference preference = client.create(preferenceRequest);

            return new PaymentResponseDTO(
                    preference.getId(),
                    preference.getInitPoint(),
                    preference.getSandboxInitPoint()
            );

        } catch (MPApiException e) {
            System.err.println("Error de API de Mercado Pago:");
            System.err.println("Status Code: " + e.getStatusCode());
            System.err.println("Response: " + e.getApiResponse());
            throw new RuntimeException("Error de API de Mercado Pago [" + e.getStatusCode() + "]: " + e.getApiResponse().getContent(), e);
        } catch (MPException e) {
            System.err.println("Error de Mercado Pago: " + e.getMessage());
            throw new RuntimeException("Error de Mercado Pago: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al crear la preferencia de pago: " + e.getMessage(), e);
        }
    }

    public PaymentResponseDTO createPreferenceFromOrder(PurchaseOrder order) {
        try {
            PreferenceClient client = new PreferenceClient();

            // Crear un item con el total de la orden
            List<PreferenceItemRequest> items = new ArrayList<>();
            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .title("Orden de Compra #" + order.getId())
                    .description("Pago de orden de compra de zapatillas")
                    .quantity(1)
                    .unitPrice(BigDecimal.valueOf(order.getTotal()))
                    .currencyId("ARS")
                    .build();
            items.add(item);

            // Configurar URLs de retorno válidas
            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://www.mercadopago.com.ar/checkout/v1/redirect?pref_id=success")
                    .failure("https://www.mercadopago.com.ar/checkout/v1/redirect?pref_id=failure")
                    .pending("https://www.mercadopago.com.ar/checkout/v1/redirect?pref_id=pending")
                    .build();

            // Crear la preferencia
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .notificationUrl(webhookUrl)
                    .externalReference(order.getId().toString())
                    .build();

            System.out.println("Creando preferencia para orden: " + order.getId());
            System.out.println("Total: " + order.getTotal());
            System.out.println("Access Token configurado: " + (accessToken != null && !accessToken.isEmpty() ? "Sí" : "No"));

            Preference preference = client.create(preferenceRequest);

            System.out.println("Preferencia creada exitosamente: " + preference.getId());

            return new PaymentResponseDTO(
                    preference.getId(),
                    preference.getInitPoint(),
                    preference.getSandboxInitPoint()
            );

        } catch (MPApiException e) {
            System.err.println("Error de API de Mercado Pago:");
            System.err.println("Status Code: " + e.getStatusCode());
            System.err.println("Response: " + e.getApiResponse().getContent());
            throw new RuntimeException("Error de API de Mercado Pago [" + e.getStatusCode() + "]: " + e.getApiResponse().getContent(), e);
        } catch (MPException e) {
            System.err.println("Error de Mercado Pago: " + e.getMessage());
            throw new RuntimeException("Error de Mercado Pago: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("Error general al crear preferencia:");
            e.printStackTrace();
            throw new RuntimeException("Error al crear la preferencia de pago para la orden: " + e.getMessage(), e);
        }
    }
} 
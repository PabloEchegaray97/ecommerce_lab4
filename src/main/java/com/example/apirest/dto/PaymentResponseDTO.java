package com.example.apirest.dto;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private String id;
    private String initPoint;
    private String sandboxInitPoint;
    private String status;
    private String statusDetail;
    private String externalReference;
    private String preferenceId;
    
    public PaymentResponseDTO(String id, String initPoint, String sandboxInitPoint) {
        this.id = id;
        this.initPoint = initPoint;
        this.sandboxInitPoint = sandboxInitPoint;
    }
} 
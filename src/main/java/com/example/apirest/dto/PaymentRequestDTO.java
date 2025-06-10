package com.example.apirest.dto;

import lombok.Data;
import java.util.List;

@Data
public class PaymentRequestDTO {
    private List<ItemDTO> items;
    private PayerDTO payer;
    private String backUrls;
    private String notificationUrl;
    
    @Data
    public static class ItemDTO {
        private String title;
        private String description;
        private Integer quantity;
        private Double unitPrice;
        private String currencyId = "ARS";
    }
    
    @Data
    public static class PayerDTO {
        private String name;
        private String surname;
        private String email;
        private PhoneDTO phone;
        private AddressDTO address;
        
        @Data
        public static class PhoneDTO {
            private String areaCode;
            private String number;
        }
        
        @Data
        public static class AddressDTO {
            private String streetName;
            private Integer streetNumber;
            private String zipCode;
        }
    }
} 
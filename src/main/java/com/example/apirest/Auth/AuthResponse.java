package com.example.apirest.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private Integer userId;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String role;
}

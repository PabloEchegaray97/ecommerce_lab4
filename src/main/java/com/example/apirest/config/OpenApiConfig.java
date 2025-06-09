package com.example.apirest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .info(new Info()
        .title("E-commerce Zapatillas API")
        .description("API REST autogenerada para gestión de e-commerce de zapatillas con Spring Boot")
        .version("1.0.0"));
    }
} 
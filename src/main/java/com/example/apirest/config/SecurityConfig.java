package com.example.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.http.HttpMethod;

import com.example.apirest.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests( authRequest ->
                authRequest
                    // Rutas de autenticación - públicas
                    .requestMatchers("/auth/**").permitAll()
                    
                    // Rutas de MercadoPago - públicas (para webhooks)
                    .requestMatchers("/pay/**").permitAll()
                    
                    // GET requests - públicos (ver productos, categorías, etc.)
                    .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/brands/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/colours/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/types/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/sizes/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/product-images/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/product-sizes/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/addresses/**").hasAuthority("ADMIN")
                    
                    // Swagger/OpenAPI - público
                    .requestMatchers("/swagger-ui/**", "/api-docs/**", "/swagger-ui.html").permitAll()
                    
                    // Perfil del usuario logueado - requiere autenticación
                    .requestMatchers(HttpMethod.GET, "/api/v1/users/profile").authenticated()
                    
                    // Direcciones del usuario logueado - requiere autenticación
                    .requestMatchers(HttpMethod.GET, "/api/v1/user-addresses/my-addresses").authenticated()
                    
                    // Órdenes del usuario logueado - requiere autenticación
                    .requestMatchers(HttpMethod.GET, "/api/v1/purchase-orders/my-orders").authenticated()
                    
                    // Crear órdenes - requiere autenticación
                    .requestMatchers(HttpMethod.POST, "/api/v1/purchase-orders").authenticated()
                    
                    // Crear detalles de órdenes - requiere autenticación
                    .requestMatchers(HttpMethod.POST, "/api/v1/details").authenticated()
                    
                    // Aprobar órdenes - requiere autenticación
                    .requestMatchers(HttpMethod.PUT, "/api/v1/purchase-orders/*/approve").authenticated()
                    
                    // Nuevos endpoints de estado para productos - solo ADMIN
                    .requestMatchers(HttpMethod.PUT, "/api/v1/products/*/activate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/products/*/deactivate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/products/*/soft-delete").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/products/*/restore").hasAuthority("ADMIN")
                    
                    // Nuevos endpoints de estado para usuarios - requiere autenticación
                    .requestMatchers(HttpMethod.PUT, "/api/v1/users/*/activate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/users/*/deactivate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/users/*/soft-delete").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/users/*/restore").authenticated()
                    
                    // Nuevos endpoints de estado para categorías - solo ADMIN
                    .requestMatchers(HttpMethod.PUT, "/api/v1/categories/*/activate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/categories/*/deactivate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/categories/*/soft-delete").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/categories/*/restore").hasAuthority("ADMIN")
                    
                    // Nuevos endpoints de estado para marcas - solo ADMIN
                    .requestMatchers(HttpMethod.PUT, "/api/v1/brands/*/activate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/brands/*/deactivate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/brands/*/soft-delete").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/brands/*/restore").hasAuthority("ADMIN")
                    
                    // Nuevos endpoints de estado para colores - solo ADMIN
                    .requestMatchers(HttpMethod.PUT, "/api/v1/colours/*/activate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/colours/*/deactivate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/colours/*/soft-delete").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/colours/*/restore").hasAuthority("ADMIN")
                    
                    // Nuevos endpoints de estado para tipos - solo ADMIN
                    .requestMatchers(HttpMethod.PUT, "/api/v1/types/*/activate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/types/*/deactivate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/types/*/soft-delete").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/types/*/restore").hasAuthority("ADMIN")
                    
                    // Nuevos endpoints de estado para tallas - solo ADMIN
                    .requestMatchers(HttpMethod.PUT, "/api/v1/sizes/*/activate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/sizes/*/deactivate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/sizes/*/soft-delete").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/sizes/*/restore").hasAuthority("ADMIN")
                    
                    // Nuevos endpoints de estado para imágenes de productos - solo ADMIN
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-images/*/activate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-images/*/deactivate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-images/*/soft-delete").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-images/*/restore").hasAuthority("ADMIN")
                    
                    // Nuevos endpoints de estado para tallas de productos - solo ADMIN
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-sizes/*/activate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-sizes/*/deactivate").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-sizes/*/soft-delete").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-sizes/*/restore").hasAuthority("ADMIN")
                    
                    // Nuevos endpoints de estado para direcciones - requiere autenticación
                    .requestMatchers(HttpMethod.PUT, "/api/v1/addresses/*/activate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/addresses/*/deactivate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/addresses/*/soft-delete").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/addresses/*/restore").authenticated()
                    
                    // Nuevos endpoints de estado para direcciones de usuarios - requiere autenticación
                    .requestMatchers(HttpMethod.PUT, "/api/v1/user-addresses/*/activate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/user-addresses/*/deactivate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/user-addresses/*/soft-delete").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/user-addresses/*/restore").authenticated()
                    
                    // Nuevos endpoints de estado para órdenes de compra - requiere autenticación
                    .requestMatchers(HttpMethod.PUT, "/api/v1/purchase-orders/*/activate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/purchase-orders/*/deactivate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/purchase-orders/*/soft-delete").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/purchase-orders/*/restore").authenticated()
                    
                    // Nuevos endpoints de estado para detalles - requiere autenticación
                    .requestMatchers(HttpMethod.PUT, "/api/v1/details/*/activate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/details/*/deactivate").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/details/*/soft-delete").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/details/*/restore").authenticated()
                    
                    // GET requests de usuarios - públicos (después de las reglas específicas)
                    .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/user-addresses/**").permitAll()
                    
                    // POST, PUT, DELETE - solo ADMIN
                    .requestMatchers(HttpMethod.POST, "/api/v1/products/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasAuthority("ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/api/v1/categories/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/categories/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/**").hasAuthority("ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/api/v1/brands/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/brands/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/brands/**").hasAuthority("ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/api/v1/colours/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/colours/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/colours/**").hasAuthority("ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/api/v1/types/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/types/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/types/**").hasAuthority("ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/api/v1/sizes/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/sizes/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/sizes/**").hasAuthority("ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/api/v1/product-images/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-images/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/product-images/**").hasAuthority("ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/api/v1/product-sizes/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/product-sizes/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/product-sizes/**").hasAuthority("ADMIN")
                    
                    // Usuarios - solo ADMIN
                    // .requestMatchers("/api/v1/users/**").hasAuthority("ADMIN")
                    // .requestMatchers("/api/v1/user-addresses/**").hasAuthority("ADMIN")
                    
                    // Cualquier otra ruta requiere autenticación
                    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
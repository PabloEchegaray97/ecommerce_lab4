package com.example.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.example.apirest.Jwt.JwtAuthenticationFilter;

// import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
// @RequiredArgsConstructor  // COMENTADO PARA PRUEBAS
public class SecurityConfig {

    // COMENTADO PARA PRUEBAS - DEPENDENCIAS JWT
    // private final AuthenticationProvider authProvider;
    // private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests( authRequest ->
                authRequest
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().permitAll() // TODAS LAS RUTAS SIN AUTENTICACIÓN PARA PRUEBAS
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // COMENTADO PARA PRUEBAS - FILTROS JWT
            // .authenticationProvider(authProvider)
            // .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
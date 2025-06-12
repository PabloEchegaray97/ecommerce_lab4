package com.example.apirest.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apirest.Jwt.JwtService;
import com.example.apirest.entities.User;
import com.example.apirest.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = (User) userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .userId(user.getId())
            .username(user.getRealUsername())
            .name(user.getName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole().name())
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .name(request.getName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(User.UserRole.CLIENT)
            .build();
            
        User savedUser = userRepository.save(user);
        String token = jwtService.getToken(savedUser);
        return AuthResponse.builder()
            .token(token)
            .userId(savedUser.getId())
            .username(savedUser.getRealUsername())
            .name(savedUser.getName())
            .lastName(savedUser.getLastName())
            .email(savedUser.getEmail())
            .role(savedUser.getRole().name())
            .build();
    }
}

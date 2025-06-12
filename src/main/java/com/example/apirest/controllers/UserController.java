package com.example.apirest.controllers;

import com.example.apirest.entities.User;
import com.example.apirest.entities.UsersAdress;
import com.example.apirest.services.UserServiceImpl;
import com.example.apirest.services.UsersAdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/users")
public class UserController extends BaseController<User, Integer> {

    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @Autowired
    private UsersAdressService usersAdressService;

    public UserController(UserServiceImpl userServiceImpl) {
        super(userServiceImpl);
    }
    
    @GetMapping("/profile")
    public ResponseEntity<?> getMyProfile() {
        try {
            // Obtener el usuario logueado desde el contexto de seguridad
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            
            // Obtener las direcciones del usuario
            List<UsersAdress> userAddresses = usersAdressService.findByUserId(currentUser.getId());
            
            // Crear respuesta sin contraseña
            Map<String, Object> response = new HashMap<>();
            response.put("id", currentUser.getId());
            response.put("name", currentUser.getName());
            response.put("lastName", currentUser.getLastName());
            response.put("username", currentUser.getRealUsername());
            response.put("email", currentUser.getEmail());
            response.put("role", currentUser.getRole().name());
            response.put("addresses", userAddresses);
            response.put("createdAt", currentUser.getCreatedAt());
            response.put("updatedAt", currentUser.getUpdatedAt());
            
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

}
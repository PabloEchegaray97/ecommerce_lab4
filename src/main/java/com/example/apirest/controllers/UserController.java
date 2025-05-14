package com.example.apirest.controllers;

import com.example.apirest.entities.User;
import com.example.apirest.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/users")
public class UserController extends BaseController<User, Integer> {

    @Autowired
    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        super(userServiceImpl);
    }

}
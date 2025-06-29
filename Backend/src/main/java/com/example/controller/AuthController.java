package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.LoginRequestDto;
import com.example.dto.RegisterRequestDto;
import com.example.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userServ;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto dto) {
        String message = userServ.register(dto);
        return ResponseEntity.ok(message);
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        String message = userServ.login(dto);
        return ResponseEntity.ok(message);
    }

}

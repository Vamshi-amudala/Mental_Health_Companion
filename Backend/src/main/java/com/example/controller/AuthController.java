package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.LoginRequestDto;
import com.example.dto.RegisterRequestDto;
import com.example.dto.ResetPasswordDto;
import com.example.service.UserService;

import jakarta.validation.Valid;

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
    

    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        userServ.sendOtp(email);
        return ResponseEntity.ok( "We've sent you a one-time code 💌\n" +
                "Check your inbox (and spam folder) for your MindMate OTP. It’s valid for 5 minutes.");
    }

    @PostMapping("/reset-password-with-otp")
    public ResponseEntity<String> resetPasswordWithOtp(@Valid @RequestBody ResetPasswordDto dto) {
    	userServ.verifyOtpAndResetPassword(dto.getEmail(), dto.getOtp(), dto.getNewPassword());
        return ResponseEntity.ok("Your MindMate password has been reset successfully. Welcome back to your safe space 💚");
    }


}

package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResetPasswordDto {
    
    @Email
    @NotBlank
    private String email;
    
    @NotNull(message = "OTP is required")
    private Long otp;

    
    @NotBlank
    private String newPassword;


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getOtp() { return otp; }
    public void setOtp(Long otp) { this.otp = otp; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}

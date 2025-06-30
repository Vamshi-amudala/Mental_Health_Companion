package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, Long otpCode) {
        String subject =  "MindMate • Your OTP to Reset Password";
        String body = "Hey there 🌱,\n\n"
                    + " We received a request to reset your password on MindMate.\n"
                    + "🔐 Your OTP is " + otpCode + "\n"
                    + "This code will expire in 5 minutes.\n\n"
                    + "If you didn’t request this, please ignore it.\n\n"
                    + "Breathe easy,\r\n"
                    + "The MindMate Team 💚";

        sendPlainEmail(toEmail, subject, body);
    }

    private void sendPlainEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
    public void sendResetPasswordConfirmationEmail(String toEmail) {
        String subject = "MindMate • Your Password Has Been Reset";
        String body = """
                Hello,

                This is to confirm that your password was successfully updated on MindMate.

                If this wasn’t you, please contact our support team immediately.

                Stay safe, stay kind 💚  
                — The MindMate Team
                """;

       
        sendPlainEmail(toEmail, subject, body);
    }
}

package com.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.LoginRequestDto;
import com.example.dto.RegisterRequestDto;
import com.example.entity.AchievementType;
import com.example.entity.User;
import com.example.exception.EmailAlreadyExistsException;
import com.example.exception.GlobalExceptionHandler;
import com.example.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BadgeService badgeService; 
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String register(RegisterRequestDto dto) {
		String email = dto.getEmail();

		try {
			if (userRepo.existsByEmail(email)) {
				throw new EmailAlreadyExistsException("Email already registered, please use different email..!");
			}
			User user = new User();
			user.setFullname(dto.getFullname());
			user.setEmail(dto.getEmail());
			user.setType(AchievementType.BADGE);
			user.setPassword(passwordEncoder.encode(dto.getPassword()));

			userRepo.save(user);
			badgeService.awardFirstLoginBadge(user);
			  
			return "Welcome to MindMate 💚 You're not alone—your journey to better mental well-being starts today.";
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Registration failed: " + e.getMessage());
		}

	}
	
	
	public String login(LoginRequestDto dto) {
	    User user = userRepo.findByemail(dto.getEmail())
	            .orElseThrow(() -> new UsernameNotFoundException("No user with this email"));

	    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid password");
	    }

	    return "Login successful! 🌿 Welcome back to MindMate, " + user.getFullname() + "! 💚";
	}
	
	public void sendOtp(String email) {
		
		User user = userRepo.findByemail(email)
				.orElseThrow(()-> new UsernameNotFoundException("No account found with this email. Please register."));
		
		Long otp =  (long) new java.security.SecureRandom().nextInt(1000000);
		user.setOtpCode(otp);
		user.setOtpExpiration(LocalDateTime.now().plusMinutes(5));
		
		userRepo.save(user);
		
		emailService.sendOtpEmail(user.getEmail(), otp);
	}
	
	 public void verifyOtpAndResetPassword(String email, Long otp, String newPassword) {
	        User user = userRepo.findByemail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
	        
	        if (user.getOtpCode() == null || !otp.equals(user.getOtpCode())) {
	            throw new BadCredentialsException("Invalid OTP");
	        }

	        if (user.getOtpExpiration() == null || user.getOtpExpiration().isBefore(LocalDateTime.now())) {
	            throw new BadCredentialsException("OTP has expired");
	        }

	        user.setPassword(passwordEncoder.encode(newPassword));
	        user.setOtpCode(null);
	        user.setOtpExpiration(null);
	        userRepo.save(user);

	        
	        emailService.sendResetPasswordConfirmationEmail(user.getEmail());
	    }


}

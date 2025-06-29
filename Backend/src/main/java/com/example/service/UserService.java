package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
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

	    return "Login successful. Welcome back, " + user.getFullname() + "! 💚";
	}

}

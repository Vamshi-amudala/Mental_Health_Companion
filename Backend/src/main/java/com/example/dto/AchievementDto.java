package com.example.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AchievementDto {
	private Long id;
	
	private Long userId;
	
	private String type;
	
	private String description;
	
	private LocalDateTime createdAt;
}

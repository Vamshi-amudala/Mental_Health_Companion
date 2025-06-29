package com.example.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchievementDto {
	private Long id;
	
	private Long userId;
	
	private String type;
	
	private String description;
	
	private LocalDateTime createdAt;
}

package com.example.dto;

import lombok.Data;

@Data
public class MoodCheckInDto {
	private Long userId;
	
	private Integer moodLevel;
		
	private String note;
}


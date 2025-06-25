package com.example.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MoodHistoryDto {
	
	private LocalDateTime date;
	
	private Integer moodLevel;
	
	private String note;
}

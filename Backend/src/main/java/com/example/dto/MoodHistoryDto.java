package com.example.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoodHistoryDto {
	
	private LocalDate date;
	
	private Integer moodLevel;
	
	private String note;
}

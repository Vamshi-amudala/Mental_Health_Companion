package com.example.dto;

import org.hibernate.validator.constraints.Range;

import com.example.entity.MoodTimeSlot;

import lombok.Data;

@Data
public class MoodCheckInDto {
	private Long userId;
	
	@Range(min = 1, max = 10, message = "Mood level must be between 1-10")
	private Integer moodLevel;
		
	private String note;
	
	 private MoodTimeSlot timeSlot;
}


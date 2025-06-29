package com.example.dto;

import java.time.LocalDate;

import com.example.entity.ActivityCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfCareActivityDto {
	
	private Long id;
	
	private Long userid;
	
	private String title;
	
	private String description;
	
	private Boolean completed;
	
	private LocalDate activityDate;
	
	private ActivityCategory category; // Added this
}

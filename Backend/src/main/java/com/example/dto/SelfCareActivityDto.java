package com.example.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SelfCareActivityDto {
	
	private Long id;
	
	private Long userid;
	
	private String title;
	
	private String description;
	
	private Boolean completed;
	
	private LocalDate activityDate;
}

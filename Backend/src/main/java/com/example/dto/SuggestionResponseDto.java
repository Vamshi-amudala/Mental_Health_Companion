package com.example.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionResponseDto {
	
	
	private String mood;
	private String suggestion;
	private List<String> steps;
}

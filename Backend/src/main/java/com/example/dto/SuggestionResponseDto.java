package com.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class SuggestionResponseDto {
    private String mood;
    private String suggestion;
    private List<String> steps;
}
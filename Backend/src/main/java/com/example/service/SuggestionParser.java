package com.example.service;

import com.example.dto.SuggestionResponseDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SuggestionParser {

    public SuggestionResponseDto parseResponse(String raw) {
        SuggestionResponseDto dto = new SuggestionResponseDto();

        String[] parts = raw.split("Steps:");
        dto.setSuggestion(parts[0].replace("Suggestion:", "").trim());

        if (parts.length > 1) {
            List<String> steps = Arrays.stream(parts[1].split("\\n"))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();
            dto.setSteps(steps);
        }

        return dto;
    }
}

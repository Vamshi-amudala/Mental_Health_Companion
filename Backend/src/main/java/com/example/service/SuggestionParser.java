package com.example.service;

import com.example.dto.SuggestionResponseDto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.*;

@Component
public class SuggestionParser {

    public SuggestionResponseDto parseResponse(String raw) {
        SuggestionResponseDto dto = new SuggestionResponseDto();
        dto.setSuggestion(extractBlock("Suggestion", raw));
        dto.setSteps(toList(extractBlock("Steps", raw)));
        return dto;
    }

    private String extractBlock(String title, String raw) {
        String pattern = "(?i)" + title + "\\s*:\\s*(.*?)(?=\\n[A-Z][a-z]+\\s*:|\\Z)";
        Pattern regex = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher matcher = regex.matcher(raw);
        return matcher.find() ? matcher.group(1).trim() : "";
    }

    private List<String> toList(String textBlock) {
        return Arrays.stream(textBlock.split("\\n|\\r|[-•\\d.]"))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();
    }
}

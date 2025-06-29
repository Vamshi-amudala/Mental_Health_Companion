package com.example.service;

import com.example.dto.AISuggestionDto;

import lombok.RequiredArgsConstructor;

import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AIService {
    private final ChatClient chatClient;

    // Get a suggestion based on mood
    public AISuggestionDto getSuggestion(String mood) {
        String prompt = "Give a short self-care suggestion for someone feeling " + mood + ".";
        String suggestion = chatClient.call(prompt);

        return new AISuggestionDto(mood, suggestion);
    }
}
package com.example.service;

import com.example.dto.AISuggestionDto;
import com.example.entity.MoodTimeSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {

    private final HuggingFaceAiService huggingFaceAiService;

    public AISuggestionDto getSuggestion(int moodLevel, MoodTimeSlot timeSlot) {
        String suggestion = huggingFaceAiService.getSuggestion(moodLevel, timeSlot);
        return new AISuggestionDto("Mood " + moodLevel + " - " + timeSlot.name(), suggestion);
    }
}

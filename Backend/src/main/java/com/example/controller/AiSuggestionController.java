package com.example.controller;

import com.example.dto.SuggestionResponseDto;
import com.example.entity.MoodTimeSlot;
import com.example.service.HuggingFaceAiService;
import com.example.service.SuggestionParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiSuggestionController {

    private final HuggingFaceAiService huggingFaceAiService;
    private final SuggestionParser suggestionParser;

    @GetMapping("/suggest")
    public ResponseEntity<SuggestionResponseDto> getSuggestion(
        @RequestParam int moodLevel,
        @RequestParam MoodTimeSlot timeSlot
    ) {
        String raw = huggingFaceAiService.getSuggestion(moodLevel, timeSlot);
        SuggestionResponseDto dto = suggestionParser.parseResponse(raw);
        dto.setMood(moodLevel + " - " + timeSlot.name());
        return ResponseEntity.ok(dto);
    }
}

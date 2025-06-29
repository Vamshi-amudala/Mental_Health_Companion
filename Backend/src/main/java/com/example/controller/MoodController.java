package com.example.controller;

import com.example.dto.MoodCheckInDto;
import com.example.dto.MoodHistoryDto;
import com.example.service.MoodService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mood")
@RequiredArgsConstructor
public class MoodController {
    private final MoodService moodService;

    @PostMapping("/checkin")
    public ResponseEntity<String> logMood(@Valid @RequestBody MoodCheckInDto dto) {
        moodService.saveMoodCheckIn(dto);
        return ResponseEntity.ok("Mood logged successfully!");
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<MoodHistoryDto>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(moodService.getMoodHistory(userId));
    }
}
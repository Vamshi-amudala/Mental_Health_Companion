package com.example.service;

import com.example.dto.MoodCheckInDto;
import com.example.dto.MoodHistoryDto;
import com.example.entity.MoodCheckIn;
import com.example.entity.User;
import com.example.exception.InvalidMoodException;
import com.example.exception.UserNotFoundException;
import com.example.repository.MoodCheckInRepo;
import com.example.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoodService {
    private final MoodCheckInRepo moodCheckInRepo;
    private final UserRepo userRepo;

    // Save a mood check-in
    public void saveMoodCheckIn(MoodCheckInDto dto) {
    	if (dto.getMoodLevel() < 1 || dto.getMoodLevel() > 10) {
            throw new InvalidMoodException("Mood level must be between 1-10");
        }

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId()));
        
        MoodCheckIn checkIn = new MoodCheckIn();
        checkIn.setUser(user);
        checkIn.setMoodLevel(dto.getMoodLevel());
        checkIn.setNote(dto.getNote());
        checkIn.setCheckInDate(LocalDate.now());

        moodCheckInRepo.save(checkIn);
    }

    // Get mood history for a user
    public List<MoodHistoryDto> getMoodHistory(Long userId) {
        return moodCheckInRepo.findByUserIdOrderByCheckInDateDesc(userId)
                .stream()
                .map(checkIn -> new MoodHistoryDto(
                        checkIn.getCheckInDate(),
                        checkIn.getMoodLevel(),
                        checkIn.getNote()
                ))
                .collect(Collectors.toList());
    }
}
package com.example.service;

import com.example.dto.AchievementDto;
import com.example.entity.*;
import com.example.exception.UserNotFoundException;
import com.example.repository.AchievementRepo;
import com.example.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AchievementService {
    private final AchievementRepo achievementRepo;
    private final UserRepo userRepo;

    // Unlock a new achievement
    public void unlockAchievement(Long userId, AchievementType type, String description) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Achievement achievement = new Achievement();
        achievement.setUser(user);
        achievement.setType(type);
        achievement.setDescription(description);
        achievement.setCreatedAt(LocalDateTime.now());

        achievementRepo.save(achievement);
    }

    // Get all achievements for a user
    public List<AchievementDto> getUserAchievements(Long userId) {
        return achievementRepo.findByUserId(userId)
                .stream()
                .map(achievement -> new AchievementDto(
                        achievement.getId(),
                        achievement.getUser().getId(),
                        achievement.getType().toString(),
                        achievement.getDescription(),
                        achievement.getCreatedAt()
                ))
                .collect(Collectors.toList());
        
    }
}
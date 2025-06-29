package com.example.service;

import com.example.entity.Streak;
import com.example.entity.StreakType;
import com.example.entity.User;
import com.example.entity.BadgeType;
import com.example.repository.StreakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class StreakService {

    @Autowired
    private StreakRepository streakRepository;

    @Autowired
    private BadgeService badgeService;

    public void updateStreak(User user, StreakType type) {
        LocalDate today = LocalDate.now();

        Optional<Streak> optionalStreak = streakRepository.findByUserAndStreakType(user, type);

        Streak streak = optionalStreak.orElseGet(() -> {
            Streak newStreak = new Streak();
            newStreak.setUser(user);
            newStreak.setStreakType(type);
            newStreak.setCurrentCount(1);
            newStreak.setLongestCount(1);
            newStreak.setLastCheckInDate(today);
            return newStreak;
        });

        if (optionalStreak.isPresent()) {
            long daysBetween = ChronoUnit.DAYS.between(streak.getLastCheckInDate(), today);

            if (daysBetween == 1) {
                streak.setCurrentCount(streak.getCurrentCount() + 1);
            } else if (daysBetween > 1) {
                streak.setCurrentCount(1);  // reset streak
            } else {
                return; // already checked in today
            }
        }

        if (streak.getCurrentCount() > streak.getLongestCount()) {
            streak.setLongestCount(streak.getCurrentCount());
        }

        streak.setLastCheckInDate(today);
        streakRepository.save(streak);

        // Optionally award streak badges for MOOD type
        if (type == StreakType.MOOD) {
            badgeService.awardMoodStreakBadge(user, streak.getCurrentCount());
        }
    }
}

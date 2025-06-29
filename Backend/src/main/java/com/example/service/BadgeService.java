package com.example.service;

import com.example.entity.Badge;
import com.example.entity.BadgeType;
import com.example.entity.User;
import com.example.repository.BadgeRepository;
import com.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private UserRepo userRepository;

    
    private static final Map<Integer, BadgeType> selfCareBadgeThresholds = new TreeMap<>(Map.of(
        5, BadgeType.SELF_CARE_5,
        10, BadgeType.SELF_CARE_10,
        30, BadgeType.SELF_CARE_30,
        50, BadgeType.SELF_CARE_50,
        75, BadgeType.SELF_CARE_75,
        100, BadgeType.SELF_CARE_100
    ));

    
    private static final Map<Integer, BadgeType> moodStreakBadgeThresholds = new TreeMap<>(Map.of(
        10, BadgeType.STREAK_10,
        50, BadgeType.STREAK_50,
        100, BadgeType.STREAK_100
    ));

    
    public void awardBadgeIfNotExists(User user, BadgeType badgeType) {
        boolean alreadyAwarded = badgeRepository.existsByUserAndBadgeType(user, badgeType);
        if (alreadyAwarded) {
            return;
        }

        Badge badge = new Badge();
        badge.setUser(user);
        badge.setBadgeType(badgeType);
        badge.setAwardedAt(LocalDateTime.now());

        badgeRepository.save(badge);
        System.out.println("Awarded badge: " + badgeType + " to user: " + user.getId());
    }

   
    public void awardSelfCareBadge(User user, int count) {
        for (Map.Entry<Integer, BadgeType> entry : selfCareBadgeThresholds.entrySet()) {
            if (count >= entry.getKey()) {
                awardBadgeIfNotExists(user, entry.getValue());
            }
        }
    }

   
    public void awardMoodStreakBadge(User user, int currentStreakCount) {
        for (Map.Entry<Integer, BadgeType> entry : moodStreakBadgeThresholds.entrySet()) {
            if (currentStreakCount >= entry.getKey()) {
                awardBadgeIfNotExists(user, entry.getValue());
            }
        }
    }

    
    public void awardFirstLoginBadge(User user) {
        awardBadgeIfNotExists(user, BadgeType.FIRST_LOGIN);
    }

    public void awardFirstCheckInBadge(User user) {
        awardBadgeIfNotExists(user, BadgeType.FIRST_CHECKIN);
    }
}

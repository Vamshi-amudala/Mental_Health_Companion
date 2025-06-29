package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.*;

import java.util.Optional;

public interface StreakRepository extends JpaRepository<Streak, Long> {
    Optional<Streak> findByUserAndStreakType(User user, StreakType streakType);
}

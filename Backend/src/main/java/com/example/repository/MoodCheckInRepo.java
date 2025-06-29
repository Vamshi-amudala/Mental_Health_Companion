package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.MoodCheckIn;
@Repository
public interface MoodCheckInRepo extends JpaRepository<MoodCheckIn, Long>{
    // This method for getting check-ins by user ordered by date
    List<MoodCheckIn> findByUserIdOrderByCheckInDateDesc(Long userId);

}

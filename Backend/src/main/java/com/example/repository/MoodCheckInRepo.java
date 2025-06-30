package com.example.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.MoodCheckIn;
import com.example.entity.*;
@Repository
public interface MoodCheckInRepo extends JpaRepository<MoodCheckIn, Long>{
   
	long countByUserId(Long userId);
    List<MoodCheckIn> findByUserIdOrderByCheckInDateDesc(Long userId);

//    Optional<MoodCheckIn> findByUserAndDateAndTimeSlot(User user, LocalDate checkInDate, MoodTimeSlot timeSlot);
    Optional<MoodCheckIn> findByUserAndCheckInDateAndTimeSlot(User user, LocalDate checkInDate, MoodTimeSlot timeSlot);

    
//    List<MoodCheckIn> findByUserAndDate(User user, LocalDate date);
    List<MoodCheckIn> findByUserAndCheckInDate(User user, LocalDate checkInDate);

}

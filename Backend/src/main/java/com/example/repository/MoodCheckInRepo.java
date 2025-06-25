package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.MoodCheckIn;
@Repository
public interface MoodCheckInRepo extends JpaRepository<MoodCheckIn, Long>{

}

package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Achievement;
@Repository
public interface AchievementRepo extends JpaRepository<Achievement, Long>{

}

package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Achievement;
import com.example.entity.User;
@Repository
public interface AchievementRepo extends JpaRepository<Achievement, Long>{
	List<Achievement> findByUserId(Long userId);
}

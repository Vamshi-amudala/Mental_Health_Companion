package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ActivityCategory;
import com.example.entity.SelfCareActivity;
@Repository
public interface SelfCareActivityRepo extends JpaRepository<SelfCareActivity, Long>{
	List<SelfCareActivity> findByUserIdAndCategory(Long userId, ActivityCategory category);
	List<SelfCareActivity> findByUserIdOrderByActivityDateDesc(Long userId);
}

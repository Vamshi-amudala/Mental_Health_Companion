package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.SelfCareActivity;
@Repository
public interface SelfCareActivityRepo extends JpaRepository<SelfCareActivity, Long>{

}

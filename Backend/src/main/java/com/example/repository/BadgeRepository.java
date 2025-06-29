package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Badge;
import com.example.entity.BadgeType;
import com.example.entity.User;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    boolean existsByUserAndBadgeType(User user, BadgeType badgeType);
    List<Badge> findByUser(User user);
}

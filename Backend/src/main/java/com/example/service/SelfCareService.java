package com.example.service;

import com.example.dto.SelfCareActivityDto;
import com.example.entity.SelfCareActivity;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.repository.SelfCareActivityRepo;
import com.example.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SelfCareService {
    private final SelfCareActivityRepo selfCareRepo;
    private final UserRepo userRepo;

    // Create a new self-care activity
    public void createActivity(SelfCareActivityDto dto) {
        User user = userRepo.findById(dto.getUserid())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        SelfCareActivity activity = new SelfCareActivity();
        activity.setUser(user);
        activity.setTitle(dto.getTitle());
        activity.setDescription(dto.getDescription());
        activity.setCompleted(dto.getCompleted());
        activity.setActivityDate(dto.getActivityDate());
        activity.setCategory(dto.getCategory());

        selfCareRepo.save(activity);
    }

    // Get all activities for a user
    public List<SelfCareActivityDto> getUserActivities(Long userId) {
        return selfCareRepo.findByUserIdOrderByActivityDateDesc(userId)
                .stream()
                .map(activity -> new SelfCareActivityDto(
                        activity.getId(),
                        activity.getUser().getId(),
                        activity.getTitle(),
                        activity.getDescription(),
                        activity.getCompleted(),
                        activity.getActivityDate(),
                        activity.getCategory()
                ))
                .collect(Collectors.toList());
    }

    // Update completion status
    public void updateActivityStatus(Long id, Boolean completed) {
        SelfCareActivity activity = selfCareRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
        activity.setCompleted(completed);
        selfCareRepo.save(activity);
    }

}
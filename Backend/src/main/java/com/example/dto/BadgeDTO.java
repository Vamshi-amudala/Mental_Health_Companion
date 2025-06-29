package com.example.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeDTO {
    private String title;
    private String description;
    private String iconName;
    private LocalDateTime earnedAt;
}

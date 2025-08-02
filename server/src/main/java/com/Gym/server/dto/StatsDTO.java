package com.Gym.server.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatsDTO {

    private int totalCalories;
    private int totalDuration;
    private double totalDistance;
    private int totalSteps;
    private int achievedGoals;
    private int notAchievedGoals;
}

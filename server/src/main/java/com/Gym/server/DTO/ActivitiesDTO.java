package com.Gym.server.DTO;

import com.Gym.server.entity.Activities;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivitiesDTO {

    private Long id;
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Activities.ActivityType activityType;
    private String activityName;
    private Integer duration;
    private Integer steps;
    private Double distance;
    private Integer caloriesBurned;
    private String equipment;
    private String notes;
    private String workoutPlan;
    private Activities.IntensityLevel intensityLevel;
}

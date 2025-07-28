package com.Gym.server.services;

import com.Gym.server.DTO.ActivitiesDTO;
import com.Gym.server.entity.Activities;
import com.Gym.server.entity.User;
import com.Gym.server.repository.ActivitiesRepository;
import com.Gym.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivitiesServiceImp implements ActivitiesService {

    private final ActivitiesRepository activitiesRepository;
    private final UserRepository userRepository;

    public ActivitiesDTO postActivities(ActivitiesDTO activityDTO) {
        // Validation
        if (activityDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        
        if (activityDTO.getStartTime() == null || activityDTO.getEndTime() == null) {
            throw new IllegalArgumentException("Start time and end time are required");
        }
        
        if (activityDTO.getStartTime().isAfter(activityDTO.getEndTime())) {
            throw new IllegalArgumentException("Start time cannot be after end time");
        }

        // Find user
        User user = userRepository.findById(activityDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Activities activities = new Activities();
        activities.setUser(user);
        activities.setStartTime(activityDTO.getStartTime());
        activities.setEndTime(activityDTO.getEndTime());
        activities.setActivityType(activityDTO.getActivityType());
        activities.setActivityName(activityDTO.getActivityName());
        activities.setSteps(activityDTO.getSteps());
        activities.setDistance(activityDTO.getDistance());
        activities.setCaloriesBurned(activityDTO.getCaloriesBurned());
        activities.setEquipment(activityDTO.getEquipment());
        activities.setNotes(activityDTO.getNotes());
        activities.setWorkoutPlan(activityDTO.getWorkoutPlan());
        activities.setIntensityLevel(activityDTO.getIntensityLevel());

        // Calculate duration if not provided
        if (activityDTO.getDuration() == null) {
            long durationMinutes = ChronoUnit.MINUTES.between(activityDTO.getStartTime(), activityDTO.getEndTime());
            activities.setDuration((int) durationMinutes);
        } else {
            activities.setDuration(activityDTO.getDuration());
        }

        // Calculate calories if not provided
        if (activityDTO.getCaloriesBurned() == null) {
            activities.setCaloriesBurned(calculateCalories(activities));
        }

        return activitiesRepository.save(activities).getActivitiesDTO();
    }

    @Override
    public ActivitiesDTO getActivityById(Long id) {
        Activities activity = activitiesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Activity not found with id: " + id));
        return activity.getActivitiesDTO();
    }

    @Override
    public List<ActivitiesDTO> getActivitiesByUser(Long userId) {
        // Verify user exists
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        
        return activitiesRepository.findByUserId(userId)
                .stream()
                .map(Activities::getActivitiesDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivitiesDTO> getAllActivities() {
        return activitiesRepository.findAll()
                .stream()
                .map(Activities::getActivitiesDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ActivitiesDTO updateActivity(Long id, ActivitiesDTO activitiesDTO) {
        Activities existingActivity = activitiesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Activity not found with id: " + id));

        // Update fields if provided
        if (activitiesDTO.getStartTime() != null) {
            existingActivity.setStartTime(activitiesDTO.getStartTime());
        }
        if (activitiesDTO.getEndTime() != null) {
            existingActivity.setEndTime(activitiesDTO.getEndTime());
        }
        if (activitiesDTO.getActivityType() != null) {
            existingActivity.setActivityType(activitiesDTO.getActivityType());
        }
        if (activitiesDTO.getActivityName() != null) {
            existingActivity.setActivityName(activitiesDTO.getActivityName());
        }
        if (activitiesDTO.getDuration() != null) {
            existingActivity.setDuration(activitiesDTO.getDuration());
        }
        if (activitiesDTO.getSteps() != null) {
            existingActivity.setSteps(activitiesDTO.getSteps());
        }
        if (activitiesDTO.getDistance() != null) {
            existingActivity.setDistance(activitiesDTO.getDistance());
        }
        if (activitiesDTO.getCaloriesBurned() != null) {
            existingActivity.setCaloriesBurned(activitiesDTO.getCaloriesBurned());
        }
        if (activitiesDTO.getEquipment() != null) {
            existingActivity.setEquipment(activitiesDTO.getEquipment());
        }
        if (activitiesDTO.getNotes() != null) {
            existingActivity.setNotes(activitiesDTO.getNotes());
        }
        if (activitiesDTO.getWorkoutPlan() != null) {
            existingActivity.setWorkoutPlan(activitiesDTO.getWorkoutPlan());
        }
        if (activitiesDTO.getIntensityLevel() != null) {
            existingActivity.setIntensityLevel(activitiesDTO.getIntensityLevel());
        }

        return activitiesRepository.save(existingActivity).getActivitiesDTO();
    }

    @Override
    public void deleteActivity(Long id) {
        if (!activitiesRepository.existsById(id)) {
            throw new IllegalArgumentException("Activity not found with id: " + id);
        }
        activitiesRepository.deleteById(id);
    }

    private int calculateCalories(Activities activity) {
        // Basic calorie calculation based on activity type and duration
        int baseCaloriesPerMinute = switch (activity.getActivityType()) {
            case CARDIO -> 8;
            case STRENGTH_TRAINING -> 6;
            case FLEXIBILITY -> 3;
            case SPORTS -> 7;
            case GROUP_CLASS -> 6;
            case PERSONAL_TRAINING -> 7;
        };

        int intensityMultiplier = switch (activity.getIntensityLevel()) {
            case LOW -> 1;
            case MODERATE -> 1;
            case HIGH -> 1;
            case EXTREME -> 1;
        };

        return (int) (baseCaloriesPerMinute * activity.getDuration() * intensityMultiplier);
    }
}

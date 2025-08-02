package com.Gym.server.service;

import com.Gym.server.dto.StatsDTO;
import com.Gym.server.entity.Activity;
import com.Gym.server.entity.Workout;
import com.Gym.server.repository.ActivityRepository;
import com.Gym.server.repository.GoalRepository;
import com.Gym.server.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImp implements DashboardService{

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public StatsDTO getDashboardStats() {
        int totalCalories = workoutRepository.getTotalCalories() + activityRepository.getTotalCalories();
        int totalDuration = workoutRepository.getTotalDuration();
        double totalDistance = activityRepository.getTotalDistance();
        int totalSteps = activityRepository.getTotalSteps();
        int achievedGoals = goalRepository.countByArchived(true);
        int notAchievedGoals = goalRepository.countByArchived(false);

        return new StatsDTO(totalCalories, totalDuration, totalDistance, totalSteps, achievedGoals, notAchievedGoals);
    }

    public List<Workout> getRecentWorkouts() {
        return workoutRepository.findTop5ByOrderByDateDesc();
    }

    public List<Activity> getRecentActivities() {
        return activityRepository.findTop5ByOrderByDateDesc();
    }

}

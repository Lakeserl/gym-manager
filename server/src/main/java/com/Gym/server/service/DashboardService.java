package com.Gym.server.service;

import com.Gym.server.dto.StatsDTO;
import com.Gym.server.entity.Activity;
import com.Gym.server.entity.Workout;

import java.util.List;

public interface DashboardService {
    StatsDTO getDashboardStats();
    List<Workout> getRecentWorkouts();
    List<Activity> getRecentActivities();
}

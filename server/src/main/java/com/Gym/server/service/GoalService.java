package com.Gym.server.service;

import com.Gym.server.dto.GoalDTO;
import com.Gym.server.entity.Goal;

import java.util.List;

public interface GoalService {
    GoalDTO postGoal(GoalDTO goalDTO);
    List<Goal> getAllGoals();
    Goal findById(Long id);
    Goal save(Goal goal);
}

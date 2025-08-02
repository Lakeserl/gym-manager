package com.Gym.server.service;

import com.Gym.server.dto.WorkoutDTO;
import com.Gym.server.entity.Workout;

import java.util.List;

public interface WorkoutService{
    WorkoutDTO postWorkout(WorkoutDTO workoutDTO);

    List<Workout> getAllWorkouts();
}

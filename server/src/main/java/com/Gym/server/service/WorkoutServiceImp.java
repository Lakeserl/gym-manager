package com.Gym.server.service;

import com.Gym.server.dto.WorkoutDTO;
import com.Gym.server.entity.Workout;
import com.Gym.server.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImp implements WorkoutService{

    private final WorkoutRepository workoutRepository;

    public WorkoutDTO postWorkout(WorkoutDTO workoutDTO){
        Workout workout = new Workout();
        workout.setDate(workoutDTO.getDate());
        workout.setType(workoutDTO.getType());
        workout.setDuration(workoutDTO.getDuration());
        workout.setCaloriesBurned(workoutDTO.getCaloriesBurned());

        return workoutRepository.save(workout).getWorkoutDTO();
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }
}

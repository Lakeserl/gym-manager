package com.Gym.server.entity;

import com.Gym.server.dto.WorkoutDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Date date;
    private int duration;
    private int caloriesBurned;

    public WorkoutDTO getWorkoutDTO() {
        WorkoutDTO workoutDTO = new WorkoutDTO();
        workoutDTO.setId(this.id);
        workoutDTO.setType(this.type);
        workoutDTO.setDate(this.date);
        workoutDTO.setDuration(this.duration);
        workoutDTO.setCaloriesBurned(this.caloriesBurned);

        return workoutDTO;
    }
}

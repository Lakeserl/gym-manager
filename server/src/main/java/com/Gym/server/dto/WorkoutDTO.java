package com.Gym.server.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class WorkoutDTO {
    private Long id;

    private String type;
    private Date date;
    private int duration;
    private int caloriesBurned;
}

package com.Gym.server.entity;

import com.Gym.server.dto.ActivityDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private int step;

    private double distance;

    private int caloriesBurned;

    public ActivityDTO  getActivityDTO() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(this.id);
        activityDTO.setDate(this.date);
        activityDTO.setStep(this.step);
        activityDTO.setDistance(this.distance);
        activityDTO.setCaloriesBurned(this.caloriesBurned);

        return activityDTO;
    }
}

package com.Gym.server.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityDTO {
    private Long id;

    private Date date;

    private int step;

    private double distance;

    private int caloriesBurned;
}

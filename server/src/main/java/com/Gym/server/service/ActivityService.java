package com.Gym.server.service;

import com.Gym.server.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {

    ActivityDTO postActivity(ActivityDTO activityDTO);

    List<ActivityDTO> getActivities();
}

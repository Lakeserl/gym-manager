package com.Gym.server.services;

import com.Gym.server.DTO.ActivitiesDTO;

import java.util.List;

public interface ActivitiesService {

    ActivitiesDTO postActivities(ActivitiesDTO activitiesDTO);
    
    ActivitiesDTO getActivityById(Long id);
    
    List<ActivitiesDTO> getActivitiesByUser(Long userId);
    
    List<ActivitiesDTO> getAllActivities();
    
    ActivitiesDTO updateActivity(Long id, ActivitiesDTO activitiesDTO);
    
    void deleteActivity(Long id);
}

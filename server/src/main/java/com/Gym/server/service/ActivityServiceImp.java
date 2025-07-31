package com.Gym.server.service;

import com.Gym.server.dto.ActivityDTO;
import com.Gym.server.entity.Activity;
import com.Gym.server.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImp implements ActivityService {

    @Autowired
    private final ActivityRepository activityRepository;

    public ActivityDTO postActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setDate(activityDTO.getDate());
        activity.setStep(activityDTO.getStep());
        activity.setDistance(activityDTO.getDistance());
        activity.setCaloriesBurned(activityDTO.getCaloriesBurned());

        return activityRepository.save(activity).getActivityDTO();
    }

    public List<ActivityDTO> getActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(Activity::getActivityDTO).collect(Collectors.toList());
    }
}

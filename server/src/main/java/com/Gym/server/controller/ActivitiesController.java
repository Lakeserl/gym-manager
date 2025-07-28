package com.Gym.server.controller;

import com.Gym.server.DTO.ActivitiesDTO;
import com.Gym.server.services.ActivitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ActivitiesController {

    private final ActivitiesService activitiesService;

    @PostMapping("/activity")
    public ResponseEntity<?> postActivities(@RequestBody ActivitiesDTO activitiesDTO) {
        try {
            ActivitiesDTO createActivity = activitiesService.postActivities(activitiesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something is wrong: " + e.getMessage());
        }
    }

    @GetMapping("/activity/{id}")
    public ResponseEntity<?> getActivityById(@PathVariable Long id) {
        try {
            ActivitiesDTO activity = activitiesService.getActivityById(id);
            return ResponseEntity.ok(activity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/activities/user/{userId}")
    public ResponseEntity<?> getActivitiesByUser(@PathVariable Long userId) {
        try {
            List<ActivitiesDTO> activities = activitiesService.getActivitiesByUser(userId);
            return ResponseEntity.ok(activities);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/activities")
    public ResponseEntity<?> getAllActivities() {
        List<ActivitiesDTO> activities = activitiesService.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    @PutMapping("/activity/{id}")
    public ResponseEntity<?> updateActivity(@PathVariable Long id, @RequestBody ActivitiesDTO activitiesDTO) {
        try {
            ActivitiesDTO updatedActivity = activitiesService.updateActivity(id, activitiesDTO);
            return ResponseEntity.ok(updatedActivity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable Long id) {
        try {
            activitiesService.deleteActivity(id);
            return ResponseEntity.ok("Activity deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

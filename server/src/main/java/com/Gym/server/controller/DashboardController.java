package com.Gym.server.controller;

import com.Gym.server.dto.StatsDTO;
import com.Gym.server.entity.Activity;
import com.Gym.server.entity.Workout;
import com.Gym.server.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<StatsDTO> getStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    @GetMapping("/recent-workouts")
    public ResponseEntity<List<Workout>> getWorkouts() {
        return ResponseEntity.ok(dashboardService.getRecentWorkouts());
    }

    @GetMapping("/recent-activities")
    public ResponseEntity<List<Activity>> getActivities() {
        return ResponseEntity.ok(dashboardService.getRecentActivities());
    }
}

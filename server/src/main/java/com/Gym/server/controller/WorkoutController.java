package com.Gym.server.controller;

import com.Gym.server.dto.WorkoutDTO;
import com.Gym.server.entity.Workout;
import com.Gym.server.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/postworkout")
    public ResponseEntity<?> postWorkout(@RequestBody WorkoutDTO workoutDTO){
        try{
            return ResponseEntity.ok(workoutService.postWorkout(workoutDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi kết nối");
        }
    }

    @GetMapping("/getallworkouts")
    public ResponseEntity<?> getAllWorkouts() {
        try {
            List<Workout> workouts = workoutService.getAllWorkouts();
            List<WorkoutDTO> workoutDTOs = workouts.stream()
                    .map(Workout::getWorkoutDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(workoutDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi tải dữ liệu");
        }
    }
}

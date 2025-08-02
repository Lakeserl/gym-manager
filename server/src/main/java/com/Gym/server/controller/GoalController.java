package com.Gym.server.controller;

import com.Gym.server.dto.GoalDTO;
import com.Gym.server.entity.Goal;
import com.Gym.server.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GoalController {

    private final GoalService goalService;

    @PostMapping("/postgoal")
    public ResponseEntity<?> postGoal(@RequestBody GoalDTO goalDTO) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(goalService.postGoal(goalDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi kết nối");
        }
    }

    @GetMapping("/getAllGoals")
    public ResponseEntity<?> getGoals() {
        try{
            List<Goal> goals = goalService.getAllGoals();
            List<GoalDTO> goalDTOS = goals.stream()
                    .map(Goal::getGoalDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(goalDTOS);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi kết nối");
        }
    }

    @PutMapping("/completegoal/{id}")
    public ResponseEntity<?> completeGoal(@PathVariable Long id) {
        try {
            Goal goal = goalService.findById(id);
            goal.setArchived(true);
            return ResponseEntity.ok(goalService.save(goal).getGoalDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy mục tiêu");
        }
    }
}

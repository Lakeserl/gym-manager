package com.Gym.server.service;

import com.Gym.server.dto.GoalDTO;
import com.Gym.server.entity.Goal;
import com.Gym.server.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalServiceImp implements GoalService {

    @Autowired
    private final GoalRepository goalRepository;

    @Override
    public GoalDTO postGoal(GoalDTO goalDTO) {
        Goal goal = new Goal();
        goal.setDescription(goalDTO.getDescription());
        goal.setStartDate(goalDTO.getStartDate());
        goal.setEndDate(goalDTO.getEndDate());
        goal.setArchived(false);
        return goalRepository.save(goal).getGoalDTO();
    }

    @Override
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    @Override
    public Goal findById(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy mục tiêu với ID: " + id));
    }

    @Override
    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }
}

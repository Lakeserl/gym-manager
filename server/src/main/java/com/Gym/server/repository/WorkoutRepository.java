package com.Gym.server.repository;

import com.Gym.server.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Long> {
    @Query("SELECT COALESCE(SUM(w.caloriesBurned), 0) FROM Workout w")
    int getTotalCalories();

    @Query("SELECT COALESCE(SUM(w.duration), 0) FROM Workout w")
    int getTotalDuration();

    List<Workout> findTop5ByOrderByDateDesc();
}

package com.Gym.server.repository;

import com.Gym.server.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    @Query("SELECT COALESCE(SUM(a.caloriesBurned), 0) FROM Activity a")
    int getTotalCalories();

    @Query("SELECT COALESCE(SUM(a.distance), 0) FROM Activity a")
    double getTotalDistance();

    @Query("SELECT COALESCE(SUM(a.step), 0) FROM Activity a")
    int getTotalSteps();

    List<Activity> findTop5ByOrderByDateDesc();
}

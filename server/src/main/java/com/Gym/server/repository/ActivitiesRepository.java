package com.Gym.server.repository;

import com.Gym.server.entity.Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities,Long> {
    
    List<Activities> findByUserId(Long userId);
}

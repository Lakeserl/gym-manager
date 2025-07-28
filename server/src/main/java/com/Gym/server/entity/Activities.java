package com.Gym.server.entity;

import com.Gym.server.DTO.ActivitiesDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "activities")
public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Liên kết với người dùng

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType; // Loại hoạt động

    @Column(nullable = false)
    private String activityName; // Tên hoạt động cụ thể

    @Column
    private Integer duration; // Thời gian tính bằng phút

    @Column
    private Integer steps; // Số bước chân (cho cardio)

    @Column
    private Double distance; // Khoảng cách (km)

    @Column
    private Integer caloriesBurned; // Calories đốt cháy

    @Column
    private String equipment; // Thiết bị sử dụng

    @Column
    private String notes; // Ghi chú

    @Column
    private String workoutPlan; // Kế hoạch tập luyện

    @Enumerated(EnumType.STRING)
    @Column
    private IntensityLevel intensityLevel; // Mức độ cường độ

    public enum ActivityType {
        CARDIO, STRENGTH_TRAINING, FLEXIBILITY, SPORTS, GROUP_CLASS, PERSONAL_TRAINING
    }

    public enum IntensityLevel {
        LOW, MODERATE, HIGH, EXTREME
    }

    public ActivitiesDTO getActivitiesDTO() {
        ActivitiesDTO activitiesDTO = new ActivitiesDTO();
        activitiesDTO.setId(id);
        activitiesDTO.setUserId(user != null ? user.getId() : null);
        activitiesDTO.setStartTime(startTime);
        activitiesDTO.setEndTime(endTime);
        activitiesDTO.setActivityType(activityType);
        activitiesDTO.setActivityName(activityName);
        activitiesDTO.setDuration(duration);
        activitiesDTO.setDistance(distance);
        activitiesDTO.setSteps(steps);
        activitiesDTO.setCaloriesBurned(caloriesBurned);
        activitiesDTO.setEquipment(equipment);
        activitiesDTO.setNotes(notes);
        activitiesDTO.setWorkoutPlan(workoutPlan);
        activitiesDTO.setIntensityLevel(intensityLevel);
        return activitiesDTO;
    }
}

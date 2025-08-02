package com.Gym.server.entity;

import com.Gym.server.dto.GoalDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Date startDate;

    private Date endDate;

    private boolean archived;

    public GoalDTO getGoalDTO() {
        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(this.id);
        goalDTO.setDescription(this.description);
        goalDTO.setStartDate(this.startDate);
        goalDTO.setEndDate(this.endDate);
        goalDTO.setArchived(this.archived);

        return goalDTO;
    }
}

package com.Gym.server.DTO;

import com.Gym.server.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime dateOfBirth;
    private User.MembershipType membershipType;
    private LocalDateTime membershipStartDate;
    private LocalDateTime membershipEndDate;
    private Boolean isActive;
} 
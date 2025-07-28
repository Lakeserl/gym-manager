package com.Gym.server.services;

import com.Gym.server.DTO.UserDTO;
import com.Gym.server.entity.User;
import com.Gym.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Validation
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (userDTO.getFullName() == null || userDTO.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (userDTO.getPhoneNumber() == null || userDTO.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }

        // Check if username already exists
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setMembershipType(userDTO.getMembershipType() != null ? userDTO.getMembershipType() : User.MembershipType.BASIC);
        user.setMembershipStartDate(userDTO.getMembershipStartDate() != null ? userDTO.getMembershipStartDate() : LocalDateTime.now());
        user.setMembershipEndDate(userDTO.getMembershipEndDate());
        user.setIsActive(userDTO.getIsActive() != null ? userDTO.getIsActive() : true);

        return convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return convertToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        // Update fields if provided
        if (userDTO.getUsername() != null && !userDTO.getUsername().trim().isEmpty()) {
            // Check if new username already exists for different user
            if (!userDTO.getUsername().equals(existingUser.getUsername()) && 
                userRepository.existsByUsername(userDTO.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getFullName() != null && !userDTO.getFullName().trim().isEmpty()) {
            existingUser.setFullName(userDTO.getFullName());
        }
        if (userDTO.getEmail() != null && !userDTO.getEmail().trim().isEmpty()) {
            // Check if new email already exists for different user
            if (!userDTO.getEmail().equals(existingUser.getEmail()) && 
                userRepository.existsByEmail(userDTO.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPhoneNumber() != null && !userDTO.getPhoneNumber().trim().isEmpty()) {
            existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getAddress() != null) {
            existingUser.setAddress(userDTO.getAddress());
        }
        if (userDTO.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(userDTO.getDateOfBirth());
        }
        if (userDTO.getMembershipType() != null) {
            existingUser.setMembershipType(userDTO.getMembershipType());
        }
        if (userDTO.getMembershipStartDate() != null) {
            existingUser.setMembershipStartDate(userDTO.getMembershipStartDate());
        }
        if (userDTO.getMembershipEndDate() != null) {
            existingUser.setMembershipEndDate(userDTO.getMembershipEndDate());
        }
        if (userDTO.getIsActive() != null) {
            existingUser.setIsActive(userDTO.getIsActive());
        }

        return convertToDTO(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setMembershipType(user.getMembershipType());
        userDTO.setMembershipStartDate(user.getMembershipStartDate());
        userDTO.setMembershipEndDate(user.getMembershipEndDate());
        userDTO.setIsActive(user.getIsActive());
        return userDTO;
    }
} 
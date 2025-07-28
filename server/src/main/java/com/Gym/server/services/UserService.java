package com.Gym.server.services;

import com.Gym.server.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    
    UserDTO getUserById(Long id);
    
    List<UserDTO> getAllUsers();
    
    UserDTO updateUser(Long id, UserDTO userDTO);
    
    void deleteUser(Long id);
} 
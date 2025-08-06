package com.cleanzcare.service;

import com.cleanzcare.dto.UserDTO;
import com.cleanzcare.dto.UserRequestDTO;
import java.util.List;

public interface UserService {
    String registerUser(UserRequestDTO dto);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    String updateUser(Long id, UserRequestDTO dto);
    String deleteUser(Long id);
}
package com.cleanzcare.service.impl;

import com.cleanzcare.dto.UserDTO;
import com.cleanzcare.dto.UserRequestDTO;
import com.cleanzcare.entities.Role;
import com.cleanzcare.entities.User;
import com.cleanzcare.repository.UserRepository;
import com.cleanzcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String registerUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            return "Email already registered.";
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // Encrypt in real apps
        user.setRoles(dto.getRoles().stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet()));

        userRepository.save(user);
        return "User registered successfully.";
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::mapToDTO).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public String updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles().stream().map(Role::valueOf).collect(Collectors.toSet()));

        userRepository.save(user);
        return "User updated successfully.";
    }

    @Override
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return "User not found.";
        }
        userRepository.deleteById(id);
        return "User deleted.";
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles().stream().map(Enum::name).collect(Collectors.toSet())
        );
    }
}
package com.cleanzcare.service;

import com.cleanzcare.dto.ProviderRequestDTO;
import com.cleanzcare.dto.ProviderResponseDTO;
import com.cleanzcare.entities.ServiceProviderDetails;
import com.cleanzcare.entities.User;
import com.cleanzcare.repository.ServiceProviderDetailsRepository;
import com.cleanzcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceProviderService {

    private final ServiceProviderDetailsRepository providerRepo;
    private final UserRepository userRepo;

    public String registerOrUpdateProvider(Long userId, ProviderRequestDTO dto) {
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) return "User not found!";

        User user = userOpt.get();

        ServiceProviderDetails details = providerRepo.findByUserId(userId).orElse(new ServiceProviderDetails());
        details.setDescription(dto.getDescription());
        details.setSkills(dto.getSkills());
        details.setExperience(dto.getExperience());
        details.setApproved(false);
        details.setUser(user);

        providerRepo.save(details);
        return "Service Provider details submitted for approval.";
    }

    public List<ProviderResponseDTO> getAllProviders() {
        return providerRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public String setApproval(Long providerId, boolean approved) {
        ServiceProviderDetails details = providerRepo.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        details.setApproved(approved);
        providerRepo.save(details);
        return approved ? "Approved successfully." : "Rejected.";
    }

    public ProviderResponseDTO getProviderByUserId(Long userId) {
        ServiceProviderDetails details = providerRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        return mapToDTO(details);
    }

    private ProviderResponseDTO mapToDTO(ServiceProviderDetails details) {
        ProviderResponseDTO dto = new ProviderResponseDTO();
        dto.setId(details.getId());
        dto.setDescription(details.getDescription());
        dto.setSkills(details.getSkills());
        dto.setExperience(details.getExperience());
        dto.setApproved(details.isApproved());
        dto.setUserId(details.getUser().getId());
        dto.setUserName(details.getUser().getName());
        return dto;
    }
}
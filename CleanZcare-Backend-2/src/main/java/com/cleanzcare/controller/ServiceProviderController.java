package com.cleanzcare.controller;

import com.cleanzcare.dto.ProviderRequestDTO;
import com.cleanzcare.dto.ProviderResponseDTO;
import com.cleanzcare.service.ServiceProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ServiceProviderController {

    private final ServiceProviderService providerService;

    @PostMapping("/register/{userId}")
    public ResponseEntity<String> registerProvider(@PathVariable Long userId, @RequestBody ProviderRequestDTO dto) {
        String result = providerService.registerOrUpdateProvider(userId, dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<ProviderResponseDTO>> getAllProviders() {
        return ResponseEntity.ok(providerService.getAllProviders());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProviderResponseDTO> getProviderByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(providerService.getProviderByUserId(userId));
    }

    @PutMapping("/approval/{providerId}")
    public ResponseEntity<String> updateApproval(@PathVariable Long providerId, @RequestParam boolean approved) {
        return ResponseEntity.ok(providerService.setApproval(providerId, approved));
    }
} 
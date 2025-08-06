package com.cleanzcare.dto;

import lombok.Data;

@Data
public class ProviderResponseDTO {
    private Long id;
    private String description;
    private String skills;
    private int experience;
    private boolean approved;
    private Long userId;
    private String userName;
    private String userEmail;
}

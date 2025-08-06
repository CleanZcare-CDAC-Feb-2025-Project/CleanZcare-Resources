package com.cleanzcare.dto;

import com.cleanzcare.entities.ApprovalStatus;
import lombok.Data;

@Data
public class ServiceProviderDTO {

    private Long id;

    // Info from ServiceProviderDetails
    private String bio;
    private String expertise;
    private int experience;
    private ApprovalStatus status;

    // Info from associated User
    private Long userId;
    private String userName;
    private String userEmail;
}
